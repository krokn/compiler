import java.io.File
import java.lang.Error

fun main(args: Array<String>) {
    scan(true)
}

data class Token (var key: Char, var value: Int){}

var list_of_one_literal = mutableListOf<String>()
var list_of_two_literal = mutableListOf<String>()
var list_of_world = mutableListOf<String>()
//----------------------------------------------
//итоговые массивы
var list_of_apostrof = mutableListOf<String>()
var list_of_indeficator = mutableListOf<String>()
var T = mutableListOf<Token>()
var index_T = 0;

var currentToken: Token = TODO()
var nextToken: Token = TODO()


/*
 C - numbers
 R - singleDelimiters
 D - doubleDelimiters
 L - literals
 K - keywords
 I - identificators
*/
fun search(stringList: MutableList<String>, searchString: String): Int {
    var index = 9999
    for (i in stringList.indices) {
        if (stringList[i] == searchString) {
            index = i
            break;
        }
    }
    return index
}

fun scanF(bool: Boolean) : Token{
    return if (bool == true){
        T[index_T]
    } else {
        index_T++
        T[index_T]
    }
}

fun FuncE()
{
    println("E func Start")
    FuncT(ref T, ref j);
    ScanPeek(ref T, ref j);
    while (!((T.type == 'R' && j == "$") || (T.type == 'R' && j == ")")))
    {
        if (T.type == 'R' && j == "+")
        {
            Scan(ref T, ref j);
            FuncT(ref T, ref j);
        }
        else
        {
            if (T.type == 'R' && j == "-")
            {
                Scan(ref T, ref j);
                FuncT(ref T, ref j);
            }
            else Err(1); // нет знаков '-' или '+'
        }
        ScanPeek(ref T, ref j);
    }
    Console.WriteLine("E func ended");
}

fun FuncT()
{
    Console.WriteLine("T func Start");
    FuncF(ref T, ref j);
    ScanPeek(ref T, ref j);
    while (!((T.type == 'R' && j == "$") || (T.type == 'R' && j == ")")
                || (T.type == 'R' && j == "+") || (T.type == 'R' && j == "-")))
    {
        ScanPeek(ref T, ref j);
        if (T.type == 'R' && j == "*")
        {
            Scan(ref T, ref j);
            FuncF(ref T, ref j);
        }
        else
        {
            if (T.type == 'R' && j == "/")
            {
                Scan(ref T, ref j);
                FuncF(ref T, ref j);
            }
            else Err(2); // нет знаков '*' или '/'
        }
        ScanPeek(ref T, ref j);
    }
    Console.WriteLine("T func ended");
}

fun FuncF()
{
    println("F func Start");
    currentToken = scanF(true);
    nextToken = scanF(false);
    if (nextToken.key == 'I') Scan(ref T, ref j);
    else
    {
        if (T.type == 'C') Scan(ref T, ref j);
        else
        {
            if (T.type == 'R' && j == "(")
            {
                Scan(ref T, ref j);
                FuncE(ref T, ref j);
                if (T.type == 'R' && j == ")") Scan(ref T, ref j);
                else Err(3); // нет закрывающей скобки
            }
            else Err(4); // нет индекса, числа или скобки
        }
    }
    Console.WriteLine("F func ended");
}





fun scan(start: Boolean){
    //Чтение из файлов
    val a = File("out/Stroka.txt")
    val main = "$a"
    val b = File("out/World.txt")
    val world = "$b"
    val c = File("out/One_literal.txt")
    val one_literal = "$c"
    val d = File("out/Two_literal.txt")
    val two_literal = "$d"
    val test = File("out/test.txt")
    var text = ""
    //------------------------------------------
    var currentToken = ""
    var graf = 0
    var i = 0
    var for_5_graf = ""
    var lexem = ""
    var apostrof = ""
    test.forEachLine { line ->
        text += line
    }
    //начальные мыссивы

    //--------------------------------------------------------
    // Токены
    File(world).useLines { lines ->
        lines.forEach { line ->
            val lineWords = line.split(" ")
            list_of_world.addAll(lineWords)
        }
    }
    File(one_literal).useLines { lines ->
        lines.forEach { line ->
            val lineWords = line.split(" ")
            list_of_one_literal.addAll(lineWords)
        }
    }
    File(two_literal).useLines { lines ->
        lines.forEach { line ->
            val lineWords = line.split(" ")
            list_of_two_literal.addAll(lineWords)
        }
    }
    while (i < text.length) {
        when (graf) {
            0 -> {
                if (text[i] == ' ') {
                    i++
                }
                if (text[i].isDigit()) {
                    graf = 1
                }
                if (text[i].isLetter()) {
                    graf = 2
                }
                if (text[i] == '#') {
                    graf = 8
                }
                if (text[i].toString() == "'") {
                    graf = 4
                }
                if (text[i].toString() in list_of_one_literal){
                    graf = 3
                }
                if (text[i] == '/' && text[i + 1] == '*') {
                    graf = 7
                }
                i--
            }
            1 -> {
                while (text[i].isDigit()){
                    currentToken += text[i]
                    i++
                }
                if (currentToken.isNotEmpty()) {
                    val number = currentToken.toInt()
                    println("C" + number + " ${number}")
                    T.add(Token('C', number))
                    currentToken = ""
                    graf = 0
                }
            }
            2 -> {
                while (text[i].isLetter()){
                    currentToken += text[i]
                    i++
                }
                i--
                if (currentToken.isNotEmpty()) {
                    val word = currentToken
                    val search_index = search(list_of_world, word)
                    if (search_index == 9999){
                        i--
                        for_5_graf = currentToken
                        graf = 5
                    }
                    else {
                        println("K" + search_index + " ${word}")
                        T.add(Token('K', search_index))
                    }
                    if(graf != 5){
                        graf = 0
                    }
                    currentToken = ""
                }
            }
            3 -> {
                if (text[i] == ':' || text[i] == '>' || text[i] == '<') {
                    lexem += text[i]
                    if (text[i + 1] == '+' || text[i + 1] == '=' ){
                        lexem += text[i + 1]
                        println("D" + search(list_of_two_literal,lexem) + " ${lexem}")
                        T.add(Token('D', search(list_of_two_literal,lexem)))
                        i++
                        lexem = ""
                        graf = 0
                    }
                    else {
                        graf = 6
                    }
                }
                else {
                    lexem += text[i]
                    graf = 6
                }
            }
            4 -> {
                i++
                while (text[i].toString() != "'"){
                    apostrof += text[i]
                    i++
                }
                list_of_apostrof.add(apostrof)
                if (apostrof in list_of_apostrof){
                    println("R" +search(list_of_apostrof,apostrof) + " ${apostrof}")
                    T.add(Token('R', search(list_of_apostrof,apostrof)))
                    apostrof = ""
                    graf = 0
                }
            }
            5 -> {
                i++
                while (text[i].isLetter() || text[i].isDigit()){
                    for_5_graf += text[i]
                    i++
                }
                if (for_5_graf.isNotEmpty()) {
                    val word = for_5_graf
                    if (word !in list_of_indeficator){
                        list_of_indeficator.add(word)
                    }
                    val search_index = search(list_of_indeficator, word)
                    T.add(Token('I', search(list_of_indeficator, word)))
                    println("I" +search(list_of_indeficator, word) + " ${word}")
                    for_5_graf = ""
                    graf = 0
                    i--
                }
            }
            6 -> {
                if (lexem in list_of_one_literal){
                    println("O" +search(list_of_one_literal,lexem) + " ${lexem}")
                    T.add(Token('O', search(list_of_one_literal,lexem)))
                    lexem = ""
                    graf = 0
                }
                i--
            }
            7 -> {
                i+=2
                while(text[i] != '*' && text[i+1] != '/')
                {
                    i++
                }
                graf = 0
                i++
            }
            8 -> {
                break
            }
        }
        i++
    }
    for (index in T){
        print(index.key)
        println(index.value)
    }
//      println(list_of_indeficator)
//    println("Массив чисел: ${list_of_numbers}")
//    println("Массив слов спец ${list_of_world_result}")
//    println("Массив индефикаторов ${list_of_world_indeficator}")
}