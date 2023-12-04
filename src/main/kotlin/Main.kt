import java.io.File
import java.lang.Error

fun main(args: Array<String>) {
    scan(true)
    FuncVl()
    println("program end successful")
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
var index_T = -1;
var buf_index_T = -1
var T_Token = Token(' ',0)
var notClosedBrackets = 0
var isLogicExpression = false

/*
 C - numbers
 K - keywords
 O - singleDelimiters
 D - doubleDelimiters
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

fun ScanPeak() {
    buf_index_T = ++index_T
}

fun Scan() : Token{
    return T[index_T]
}


fun element (token: Token) : String {
    var result: String = ""
    when(token.key) {
        'C' -> {
            result = token.value.toString()
        }
        'O' -> {
            result = list_of_one_literal[token.value]
        }
        'D' -> {
            result = list_of_two_literal[token.value]
        }
        'K' -> {
            result = list_of_world[token.value]
        }
        'I' -> {
            result = list_of_indeficator[token.value]
        }
    }
    return result
}

fun besconechost () {
    while (true) {}
}

fun FuncE()
{
    println("E func Start $index_T")
    FuncT()
    index_T++ // МБ придется убрать
    println("E func Start после T $index_T")
    while (!((T[index_T].key == 'O' && element(T[index_T]) == "$") || (T[index_T].key == 'O' && element(T[index_T]) == ")")))
    {
        println("Зашли в цикл E $index_T")
        if ((((T[index_T].key == 'O' && element(T[index_T]) == ">") || (T[index_T].key == 'O' && element(T[index_T]) == "<") || (T[index_T].key == 'O' && element(T[index_T]) == "=") || (T[index_T].key == 'D' && element(T[index_T]) == ">=")
                    || (T[index_T].key == 'D' && element(T[index_T]) == "<=") || (T[index_T].key == 'D' && element(T[index_T]) == "<>") || (T[index_T].key == 'O' && element(T[index_T]) == "]")) && isLogicExpression))
        {
            println("E func returned")
            return
        }
        else {
            if (T[index_T].key == 'O' && element(T[index_T]) == "+")
            {
                FuncT()
            }
            else
            {
                if (T[index_T].key == 'O' && element(T[index_T]) == "-")
                {
                    FuncT()
                }
                else Err(1); // нет знаков '-' или '+'
            }
            index_T++ // МБ придется убрать
        }
    }
    if (notClosedBrackets == 0 && element(T[index_T]) == ")")
        Err(11); // Too many closing brackets
    println("E func ended $index_T");
}

fun FuncT()
{
    println("T func Start $index_T")
    FuncF()
    index_T++
    while (!((T[index_T].key == 'O' && element(T[index_T]) == "$") || (T[index_T].key == 'O' && element(T[index_T]) == ")")
                || (T[index_T].key == 'O' && element(T[index_T]) == "+") || (T[index_T].key == 'O' && element(T[index_T]) == "-")))
    {
        println("Зашли в цикл T $index_T")
        if ((((T[index_T].key == 'O' && element(T[index_T]) == ">") || (T[index_T].key == 'O' && element(T[index_T]) == "<") || (T[index_T].key == 'O' && element(T[index_T]) == "=") || (T[index_T].key == 'D' && element(T[index_T]) == ">=")
                    || (T[index_T].key == 'D' && element(T[index_T]) == "<=") || (T[index_T].key == 'D' && element(T[index_T]) == "<>") || (T[index_T].key == 'O' && element(T[index_T]) == "]")) && isLogicExpression))
        {
            println("F func returned")
            return
        }
        else {
            if (T[index_T].key == 'O' && element(T[index_T]) == "*")
            {
                FuncF()
            }
            else
            {
                if (T[index_T].key == 'O' && element(T[index_T]) == "/")
                {
                    FuncF()
                }
                else Err(2); // нет знаков '*' или '/'
            }
        }
        // Тут был Index_T++
        index_T++
    }
    println("T func ended $index_T");
}


fun FuncF()
{
    println("F func Start $index_T");
    index_T++
    if (T[index_T].key == 'I') println("прошел I $index_T")
    else
    {
        if (T[index_T].key == 'C') println("прошел C $index_T")
        else
        {
            if (T[index_T].key == 'O' && element(T[index_T]) == "(")
            {
                println("прошел O $index_T")
                notClosedBrackets++
                FuncE()
                if (T[index_T].key == 'O' && element(T[index_T]) == ")") notClosedBrackets-- // тут был index_T--
                else Err(3); // нет закрывающей скобки
            }
            else Err(4); // нет индекса, числа или скобки
        }
    }
    println("F func ended $index_T");
}

fun FuncVl()
{
    println("Vl func Start")
    FuncTl()
    index_T++
    while (!((T[index_T].key == 'O' && element(T[index_T]) == "$") || (T[index_T].key == 'O' && element(T[index_T]) == ")")))
    {
        if (T[index_T].key == 'K' && element(T[index_T]) == "or")
        {
            FuncTl()
        }
        else
        {
            Err(11); // Ожидалось 'or'
        }
        index_T++
    }
    if (notClosedBrackets == 0 && element(T[index_T]) == ")")
        Err(11); // Too many closing brackets
    println("Vl func ended");
}

fun FuncTl()
{
    println("Tl func Start");
    FuncFl();
    index_T++
    while (!(T[index_T].key == 'O' && element(T[index_T]) == "$") || (T[index_T].key == 'O' && element(T[index_T]) == ")") || (T[index_T].key == 'K' && element(T[index_T]) == "or"))
    {
        if (T[index_T].key == 'K' && element(T[index_T]) == "and")
        {
            FuncFl();
        }
        else
        {
            Err(10); // Ожидалось 'and'
        }
    }
    println("Tl func ended");
}


fun FuncFl()
{
    println("Fl func Start");
    index_T++
    if (T[index_T].key == 'I')
    else
    {
        if (T[index_T].key == 'K' && element(T[index_T]) == "true")
        else
        {
            if (T[index_T].key == 'K' && element(T[index_T]) == "false")
            else
            {
                if (T[index_T].key == 'O' && element(T[index_T]) == "(")
                {
                    notClosedBrackets++
                    FuncVl()
                    if (!(T[index_T].key == 'O' && element(T[index_T]) == ")")) Err(3); // ожидалось )
                    else
                    {
                        notClosedBrackets--;
                    }
                }
                else
                {
                    if (T[index_T].key == 'K' && element(T[index_T]) == "not")
                    {
                        FuncFl()
                    }
                    else
                    {
                        if (T[index_T].key == 'O' && element(T[index_T]) == "[")
                        {
                            isLogicExpression = true;
                            FuncE()
                            FuncZo()
                            FuncE()
                            isLogicExpression = false;
                            if (T[index_T].key == 'O' && element(T[index_T]) == "]")
                            else Err(9); // ожидалось ]
                        }
                        else
                        {
                            Err(10); // ожидалось I, 'true', 'false', FuncVl(), 'not'FuncFl() или [FuncE()]
                        }
                    }
                }
            }
        }
    }
    println("Fl func ended");
}


fun FuncZo()
{
    println("Zo func Start");
    index_T++
    if (T[index_T].key == 'O')
    {
        when (element(T[index_T]))
        {
            "=" -> {
                index_T++
            }
            ">" -> {
                index_T++
            }
            "<" -> {
                index_T++
            }
            else -> {
                Err(6);
            }
        }
    }
    else
    {
        if (T[index_T].key == 'D')
        when (element(T[index_T]))
        {
            ">=" -> {
                index_T++
            }
            "<=" -> {
                index_T++
            }
            "<>" -> {
                index_T++
            }
            else -> {
                Err(7);
            }
        }
        else Err(8)
    }
    println("Zo func ended");
}



fun Err (x: Int) {
    when(x){
        0 -> {
            println("Error 0")
            besconechost()
        }
        1 -> {
            println("Error 1")
            besconechost()
        }
        2 -> {
            println("Error 2")
            besconechost()
        }
        3 -> {
            println("Error 3")
            besconechost()
        }
        4 -> {
            println("Error 4")
            besconechost()
        }
        5 -> {
            println("Error 5")
            besconechost()
        }
        6 -> {
            println("Error 6")
            besconechost()
        }
        7 -> {
            println("Error 7")
            besconechost()
        }
        8 -> {
            println("Error 8")
            besconechost()
        }
        9 -> {
            println("Error 9")
            besconechost()
        }
        10 -> {
            println("Error 10")
            besconechost()
        }
        11 -> {
            println("Error 11")
            besconechost()
        }
        12 -> {
            println("Error 12")
            besconechost()
        }
        13 -> {
            println("Error 13")
            besconechost()
        }
        14 -> {
            println("Error 14")
            besconechost()
        }
        15 -> {
            println("Error 15")
            besconechost()
        }
        16 -> {
            println("Error 16")
            besconechost()
        }
        17 -> {
            println("Error 17")
            besconechost()
        }
        18 -> {
            println("Error 18")
            besconechost()
        }
        19 -> {
            println("Error 19")
            besconechost()
        }
    }
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
                i--
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
//    for (index in T){
//        print(index.key)
//        println(index.value)
//    }
//      println(list_of_indeficator)
//    println("Массив чисел: ${list_of_numbers}")
//    println("Массив слов спец ${list_of_world_result}")
//    println("Массив индефикаторов ${list_of_world_indeficator}")
}