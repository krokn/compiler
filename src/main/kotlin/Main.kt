import java.io.File

fun main(args: Array<String>) {
    scan(true)
    Program()
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
var index_T = 0;
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
fun Program()
{
    println("Program Started");

    if (T[index_T].key == 'K' && T[index_T].value == list_of_world.indexOf("program")) index_T++
    else Err(12); // 'program' expected

    if (T[index_T].key == 'I') index_T++
    else Err(13); // A variable expected as the program name

    if (T[index_T].key == 'O' && T[index_T].value == list_of_one_literal.indexOf(";")) index_T++
    else Err(20); // ; expected

    FuncVDB();
    FuncOB();

    if (T[index_T].key == 'K' && T[index_T].value == list_of_world.indexOf("end")) index_T++
    else Err(14);

    if (T[index_T].key == 'O' &&  T[index_T].value == list_of_one_literal.indexOf(".")) index_T++
    else Err(15); // '.' expected

    println("Program Ended");
}
// Блок декларации
fun FuncVDB() // Variables Declaration Block
{
    println("Variables Declaration Block Started");
    if (T[index_T].key == 'K' && T[index_T].value == list_of_world.indexOf("var")) index_T++
    else Err(16); // 'var' expected

    while (!(T[index_T].key == 'K' && T[index_T].value == list_of_world.indexOf("begin")))
    {
        FuncVB()
        if (!(T[index_T].key == 'O' && T[index_T].value == list_of_one_literal.indexOf(";")))
            break;
        while (T[index_T].key  == 'O' && T[index_T].value  == list_of_one_literal.indexOf(";"))
        {
            index_T++
        }
    }
   println("Variables Declaration Block Ended");
}
// Variable Block
fun FuncVB()
{
    println("Variable Block Started");

    if (T[index_T].key == 'I') index_T++
    else Err(17); // A variable expected

    while (!(T[index_T].key == 'O' && T[index_T].value == list_of_one_literal.indexOf(":")))
    {
        if (T[index_T].key == 'O' && T[index_T].value == list_of_one_literal.indexOf(","))
        {
            index_T++
            if (T[index_T].key == 'I') index_T++
            else Err(18); // A variable expected
        }
    }

    if (T[index_T].key == 'O' && T[index_T].value == list_of_one_literal.indexOf(":")) index_T++
    else Err(19); // ':' expected

    FuncType()

    println("Variable Block Ended");
}
// TIP
fun FuncType()
{
    println("Type Started");

    if (T[index_T].key == 'K' && T[index_T].value == list_of_world.indexOf("array")) // Array
    {
        index_T++
        if (T[index_T].key == 'O' && T[index_T].value == list_of_one_literal.indexOf("[")) index_T++
        else Err(21); // '[' expected

        if (T[index_T].key == 'C') index_T++
        else Err(22); // A number expected

        if (T[index_T].key == 'D' && T[index_T].value == list_of_two_literal.indexOf("..")) index_T++
        else Err(23); // ':' expected

        if (T[index_T].key == 'C') index_T++
        else Err(24); // A number expected

        if (T[index_T].key == 'O' && T[index_T].value == list_of_one_literal.indexOf("]")) index_T++
        else Err(25); // ']' expected

        if (T[index_T].key == 'K' && T[index_T].value == list_of_world.indexOf("of")) index_T++
        else Err(26); // 'of' expected

    }
    else        //Simple
    {
        when (element(T[index_T]))
        {
            "integer" -> {
                index_T++
            }
            "char" -> {
                index_T++
            }
            "boolean" -> {
                index_T++
            }
            else -> {
                Err(27); // any simple type expected
            }
        }
    }
    println("Type Ended");
}
// Operators Block (Main)
fun FuncOB()
{
    println("\nMain Operators Block Started");

    if (T[index_T].key == 'K' && T[index_T].value == list_of_world.indexOf("begin")) index_T++
    else Err(28); // 'begin' expected

    while (!(T[index_T].key == 'K' && T[index_T].value == list_of_world.indexOf("end"))) {
        FuncOperator()
        index_T++
        if (!(T[index_T].key == 'O' && T[index_T].value == list_of_one_literal.indexOf(";"))) // || (T.type == 'K' && j == "end")
        {
            Err(29); // ';' expected
        }
        while (T[index_T].key == 'O' && T[index_T].value == list_of_one_literal.indexOf(";")) {
            index_T++
        }
    }

    println("Main Operators Block Ended");
}
// OPR
fun FuncOperator()
{
    println("\nFunc Operator Started");

    if (T[index_T].key == 'I') // поменять на if (I нельзя проверить)
    {
        OprAssinment()
    }
    else
    {
        if (T[index_T].key == 'K' && T[index_T].value == list_of_world.indexOf("let"))
        {
            index_T++
            OprAssinment()
        }
        else
        {
            if (T[index_T].key == 'K' && T[index_T].value == list_of_world.indexOf("while"))
            {
                OprWhile()
            }
            else
            {
                if (T[index_T].key == 'K' && T[index_T].value == list_of_world.indexOf("if"))
                {
                    OprIf()
                }
                else
                {
                    if (T[index_T].key == 'K' && T[index_T].value == list_of_world.indexOf("read"))
                    {

                    }
                    else
                    {
                        if (T[index_T].key == 'K' && T[index_T].value == list_of_world.indexOf("write"))
                        {

                        }
                        else
                        {
                            if (T[index_T].key == 'K' && T[index_T].value == list_of_world.indexOf("begin"))
                            {
                                FuncOB()
                            }
                            else
                            {
                                Err(0);
                            }
                        }
                    }
                }
            }
        }
    }
    println("Func Operator Ended");
}
fun OprAssinment() {
    println("Operator Assignment Started");

    if (T[index_T].key == 'I') index_T++
    else Err(31); // A varible expected

    if (T[index_T].key == 'D' && T[index_T].value == list_of_two_literal.indexOf(":=")) index_T++
    else Err(32); // ':=' expected

    var bracketsCount = 0;
    while (T[index_T].key == 'O' && T[index_T].value == list_of_one_literal.indexOf("(")) {
        // МБ НУЖНО БУДЕТ ЧТО ТО ДОБАВИТЬ
        bracketsCount++;
        index_T += bracketsCount
    }
    index_T += bracketsCount
    if (T[index_T].key == 'C') {
        FuncE()
    } else {
        if ((T[index_T].key == 'K' && T[index_T].value == list_of_world.indexOf("true") || T[index_T].value == list_of_world.indexOf(
                "false"
            ) || T[index_T].value == list_of_world.indexOf("not"))
            || (T[index_T].key == 'O' && T[index_T].value == list_of_one_literal.indexOf("["))
        ) {
            FuncVl()
        } else {
            if (T[index_T].key == 'O' && T[index_T].value == list_of_one_literal.indexOf("'")) {
                // МБ НУЖНО БУДЕТ ЧТО ТО ДОБАВИТЬ
                for (i in 0..3) {
                    index_T++
                }
                if (!(T[index_T].key == 'O' && T[index_T].value == list_of_one_literal.indexOf("'")))
                    Err(33); // ' expected
            } else {
                if (T[index_T].key == 'I') {
                    index_T += bracketsCount + 1
                    println("\n" + T[index_T].key + " " + T[index_T].value + " " + element(T[index_T]) + "\n");
                    if (T[index_T].key == 'O' && T[index_T].value == list_of_one_literal.indexOf(";")) {
                        index_T++
                    } else {
                        if (T[index_T].key == 'K' && (T[index_T].value == list_of_world.indexOf("and") || T[index_T].value == list_of_world.indexOf(
                                "or"
                            ))
                        ) {
                            FuncVl()
                        } else {
                            if (T[index_T].key == 'O' && (T[index_T].value == list_of_one_literal.indexOf("+") || T[index_T].value == list_of_one_literal.indexOf(
                                    "-"
                                )
                                        || T[index_T].value == list_of_one_literal.indexOf("/") || T[index_T].value == list_of_one_literal.indexOf(
                                    "*"
                                ))
                            ) {
                                FuncE()
                            } else {
                                if (T[index_T].key == 'O' && T[index_T].value == list_of_one_literal.indexOf(")")) {
                                    FuncE()
                                    // FuncVl()
                                } else Err(35);
                            }
                        }
                    }
                } else Err(34);
            }
        }
    }
    println("Operator Assignment Ended");
}
fun OprWhile()
{
    println("Operator While Started");
    index_T++
    FuncVl();
    if (T[index_T].key == 'K' && T[index_T].value == list_of_world.indexOf("do"))
    {
        index_T++
        FuncOperator()
    }
    else Err(36);
    println("Operator While Ended");
}
fun OprIf()
{
    println("Operator if Started");

    index_T++
    FuncVl()
    if (T[index_T].key == 'K' && T[index_T].value == list_of_world.indexOf("then"))
    {
        index_T++
        FuncOperator()
        //ScanPeekX(ref T, ref j, 1); нужно заменить
        if (T[index_T].key == 'K' && T[index_T].value == list_of_world.indexOf("else"))
        {
            index_T++
            if (!(T[index_T].key == 'O' && T[index_T].value == list_of_world.indexOf(";")))
            {
                Err(29); // ';' expected
            }
            println("else Started");

            index_T++
            FuncOperator()

            println("else Ended");
        }
    }
    else Err(36);

    println("Operator if Ended");
}
fun FuncE()
{
    println("E func Start $index_T")
    FuncT()
    while (!((T[index_T].key == 'O' && element(T[index_T]) == "#") || (T[index_T].key == 'O' && element(T[index_T]) == ")") || (T[index_T].key == 'O' && element(T[index_T]) == ";")))
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
                index_T++
                FuncT()
            }
            else
            {
                if (T[index_T].key == 'O' && element(T[index_T]) == "-")
                {
                    index_T++
                    FuncT()
                }
                else {
                    if (!isLogicExpression) {
                        Err(1)
                    }
                    println("E func returned")
                    return
                }; // нет знаков '-' или '+'
            }
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
    while (!((T[index_T].key == 'O' && element(T[index_T]) == "#") || (T[index_T].key == 'O' && element(T[index_T]) == ")")
                || (T[index_T].key == 'O' && element(T[index_T]) == "+") || (T[index_T].key == 'O' && element(T[index_T]) == "-") || (T[index_T].key == 'O' && element(T[index_T]) == ";")))
    {
        println("Зашли в цикл T $index_T")
        if ((((T[index_T].key == 'O' && element(T[index_T]) == ">") || (T[index_T].key == 'O' && element(T[index_T]) == "<") || (T[index_T].key == 'O' && element(T[index_T]) == "=") || (T[index_T].key == 'D' && element(T[index_T]) == ">=")
                    || (T[index_T].key == 'D' && element(T[index_T]) == "<=") || (T[index_T].key == 'D' && element(T[index_T]) == "<>") || (T[index_T].key == 'O' && element(T[index_T]) == "]")) && isLogicExpression))
        {
            println("T func returned")
            return
        }
        else {
            if (T[index_T].key == 'O' && element(T[index_T]) == "*")
            {
                index_T++
                FuncF()
            }
            else
            {
                if (T[index_T].key == 'O' && element(T[index_T]) == "/")
                {
                    index_T++
                    FuncF()
                }
                else {
                    if (!isLogicExpression){
                        Err(2)
                    }
                    println("T funk returned")
                }; // нет знаков '*' или '/'
            }
        }
    }
    println("T func ended $index_T");
}
fun FuncF()
{
    println("F func Start $index_T");
    if (T[index_T].key == 'I') {
        index_T++
        if (T[index_T].key == 'O' && element(T[index_T]) == "[") {
            index_T++
            FuncE()
            index_T++
            if (!(T[index_T].key == 'O' && element(T[index_T]) == "]")) Err(5)
        }
    }
    else
    {
        if (T[index_T].key == 'C') index_T++
        else
        {
            if (T[index_T].key == 'O' && element(T[index_T]) == "(")
            {
                index_T++
                notClosedBrackets++
                FuncE()
                if (T[index_T].key == 'O' && element(T[index_T]) == ")") {
                    notClosedBrackets--
                    index_T++
                } // тут был index_T--
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
    while (!((T[index_T].key == 'O' && element(T[index_T]) == "#") || (T[index_T].key == 'O' && element(T[index_T]) == ")") || (T[index_T].key == 'O' && element(T[index_T]) == ";") || (T[index_T].key == 'K' && element(T[index_T]) == "end")
                || (T[index_T].key == 'K' && element(T[index_T]) == "do") || (T[index_T].key == 'K' && element(T[index_T]) == "then") || (T[index_T].key == 'O' && element(T[index_T]) == ";")))
    {
        if (T[index_T].key == 'K' && element(T[index_T]) == "or")
        {
            index_T++
            FuncTl()
        }
        else
        {
            Err(10); // Ожидалось 'or'
        }
    }
    if (notClosedBrackets == 0 && element(T[index_T]) == ")")
        Err(11); // Too many closing brackets
    println("Vl func ended");
}
fun FuncTl()
{
    println("Tl func Start");
    FuncFl();
    while (!((T[index_T].key == 'O' && element(T[index_T]) == "#") || (T[index_T].key == 'O' && element(T[index_T]) == ")") || (T[index_T].key == 'K' && element(T[index_T]) == "or") || (T[index_T].key == 'O' && element(T[index_T]) == ";")
                || (T[index_T].key == 'K' && element(T[index_T]) == "end") || (T[index_T].key == 'K' && element(T[index_T]) == "do") || (T[index_T].key == 'K' && element(T[index_T]) == "then") || (T[index_T].key == 'O' && element(T[index_T]) == ";")))
    {
        if (T[index_T].key == 'K' && element(T[index_T]) == "and")
        {
            index_T++
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
    if (T[index_T].key == 'I') index_T++
    else
    {
        if (T[index_T].key == 'K' && element(T[index_T]) == "true") index_T++
        else
        {
            if (T[index_T].key == 'K' && element(T[index_T]) == "false") index_T++
            else
            {
                if (T[index_T].key == 'O' && element(T[index_T]) == "(")
                {
                    index_T++
                    notClosedBrackets++
                    FuncVl()
                    index_T++
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
                        index_T++
                        FuncFl()
                    }
                    else
                    {
                        if (T[index_T].key == 'O' && element(T[index_T]) == "[")
                        {
                            isLogicExpression = true;
                            index_T++
                            FuncE()
                            FuncZo()
                            FuncE()
                            isLogicExpression = false;
                            if (T[index_T].key == 'O' && element(T[index_T]) == "]") index_T++
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
                    T.add(Token('O', search(list_of_one_literal,text[i].toString())))
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
//    println(list_of_indeficator)
//    println("Массив чисел: ${list_of_numbers}")
//    println("Массив слов спец ${list_of_world_result}")
//    println("Массив индефикаторов ${list_of_world_indeficator}")
}