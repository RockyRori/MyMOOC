package neu.edu.mymooc;

public class Question {
    private String question;
    private String[] choice;
    private String answer;
    public Question(String question, String[] choice, String answer){
        this.question = question;
        this.choice = choice;
        this.answer = answer;
    }
    public String getQuestion() {
        return question;
    }
    public String[] getChoice() {return choice;}
    public String getAnswer() {
        return answer;
    }
    static public Question[] getSingleChoiceQuestionList(){
        Question[] singleChoiceQuestion ={
                new Question("1.运动损伤按病程可分为急性损伤和____?", new String[]{"A.重度损伤","B.慢性损伤","C.轻度损伤","D.中度损伤"},"B.慢性损伤"),
                new Question("2.轻度运动损伤后，应马上采取()措施？", new String[]{"A.针灸","B.冷敷","C.按摩 ","D.热敷"},"B.冷敷"),
                new Question("3.正手发球不能发哪种球？", new String[]{"A.网前球","B.平高球","C.平射球","D.高远球"},"C.平射球"),
                new Question("4.反手发球不能发哪种球？", new String[]{"A.后场球","B.平射球","C.网前球","D.高远球"},"D.高远球"),
                new Question("5.右手正手握拍时，虎口应对准拍柄（）的突起小棱", new String[]{"A.宽面左侧","B.宽面右侧","C.窄面左侧","D.窄面右侧"},"C.窄面左侧"),
                new Question("6.现代羽毛球起源于哪个国家？", new String[]{"A.中国","B.法国","C.英国","D.美国"},"C.英国"),
                new Question("7.第一个正规的羽毛球协会在哪个国家成立？", new String[]{"A.中国","B.印度","C.英国","D.美国"},"C.英国"),
                new Question("8.我国在哪一届奥运会上首次夺得羽毛球项目奥运金牌？", new String[]{"A.1992","B.1996","C.2000","D.2004"},"B.1996"),
                new Question("9.羽毛球场的长度是____？", new String[]{"A.11.8","B.12.6","C.13.4","D.14.0"},"C.13.4"),
                new Question("10.羽毛球场的宽度是____？", new String[]{"A.5.6","B.6.1","C.6.4","D.7.2"},"B.6.1")
                };
        return singleChoiceQuestion;
    }

    static public Question[] getMultipleChoiceQuestionList(){
        Question[] multipleChoiceQuestion = {
                new Question("1.在计算机中，稀疏矩阵的存储表示和实现包括___。", new String[]{"A.三元组顺序表","B.行或列为主序","C.行逻辑链接的顺序表","D.十字链表"},"TFTT：ACD"),
                new Question("2.关于一个非空广义表的表尾，以下说法不正确的是", new String[]{"A.只能是原子","B.只能是子表","C.原子或子表","D.既非原子也非子表"},"TFTT：ACD"),
                new Question("3.下列哪些排序方法是稳定的?", new String[]{"A.直接插入排序","B.冒泡排序","C.归并排序","D.基数排序"},"TTTT：ABCD"),
                new Question("4.下列哪些排序方法是不稳定的?", new String[]{"A.冒泡排序","B.希尔排序","C.快速排序","D.堆排序"},"FTTT：BCD"),
                new Question("5.在哈希表查找中处理冲突时，开放定址法包括___等方法。", new String[]{"A.线性探测再散列","B.二次探测再散列","C.再哈希法","D.伪随机数探测再散列"},"TTFT：ABD"),
                new Question("6.下列关于二叉排序树的说法正确的是___。", new String[]{"A.中序遍历二叉排序树可以得到一个关键字的有序序列","B.若它的右子树不空，则右子树所有结点的值均大于根结点的值","C.它的左、右子树均为二叉排序树","D.它的平均查找长度与树的形态有关"},"TTTT：ABCD"),
                new Question("7.以下___操作属于串类型的最小操作子集。", new String[]{"A.串赋值StrAssign","B.串定位Index","C.求串长StrLength","D.串复制StrCopy"},"TFTF：AC"),
                new Question("8.下面关于字符串的叙述，不正确的有___。", new String[]{"A.字符串是不少于一个字符的序列","B.字符串是由字母和数字组成的序列","C.字符串是由零个或多个字符组成的有限序列","D.字符串是任意个字母组成的序列"},"TTFT：ABD"),
                new Question("9.一个栈的入栈序列是a、b、c、d、e，则栈的可能输出的序列是___。", new String[]{"A.a、b、c、d、e","B.d、c、e、a、b ","C.d、e、c、b、a  ","D.e、d、c、b、a"},"TFTT：ACD"),
                new Question("10.一个队列的入列序列是a、b、c、d、e，则队列的输出序列不可能是___。", new String[]{"A.a、b、c、d、e","B.a、c、b、d、e","C.a、b、d、c、e","D.e、d、c、b、a"},"FTTT：BCD")
        };
        return multipleChoiceQuestion;
    }

    static public Question[] getJudgementQuestionList(){
        Question[] judgementChoiceQuestion = {
                new Question("1.若广义表中的每个数据元素都是原子，则广义表就是线性表。", new String[]{"A.√","B. X"},"A.√"),
                new Question("2.非零元素可用三元组（行下标，列下标，元素值）表示，利用它能够恢复一个稀疏矩阵。", new String[]{"A.√","B. X"},"B. X"),
                new Question("3.依据排序所需的工作量，先进排序方法的时间复杂度是O(nlogn)", new String[]{"A.√","B. X"},"A.√"),
                new Question("4.堆排序是一种基于插入的排序方法。", new String[]{"A.√","B. X"},"B. X"),
                new Question("5.快速排序是不稳定的。", new String[]{"A.√","B. X"},"A.√"),
                new Question("6.基数排序是基于关键字比较的排序。", new String[]{"A.√","B. X"},"B. X"),
                new Question("7.如果用low和high分别指向搜索区间的下界和上界，折半查找失败的判定条件是low>high的结果为真值。", new String[]{"A.√","B. X"},"A.√"),
                new Question("8.含有n（n>2）个结点的二叉排序树是唯一的。", new String[]{"A.√","B. X"},"B. X"),
                new Question("9.哈希表查找的基本思想是按记录的关键字的值决定记录的存储地址。", new String[]{"A.√","B. X"},"A.√"),
                new Question("10.当二叉排序树蜕变为单支树时，其平均查找长度与顺序查找相同。", new String[]{"A.√","B. X"},"A.√"),
                new Question("11.与顺序表查找类似，哈希表的平均查找长度是表中记录数n的函数。", new String[]{"A.√","B. X"},"B. X")
        };
        return judgementChoiceQuestion;
    }

    static public Question[] getWrittenAnswerQuestionList(){
        Question[] writtenAnswerQuestion = {
                new Question("1.一个100*100的对称矩阵，采用压缩存储，所需保存的数据量大小是___。", null,"5050"),
                new Question("2.已知广义表GL = (a，(b，c，d，e)，f，g)，下列运算head(head(tail(GL)))的结果是___。", null,"b"),
                new Question("3.大多数的排序算法都包含两个基本操作：比较和___。", null,"交换"),
                new Question("4.两个关键字相等的记录，如果在排序前后的位置发生了逆变，这类排序称为___的排序方法。", null,"不稳定"),
                new Question("5.根据排序的原则，内部排序可以分为插入排序、___、选择排序、归并排序和基数排序。", null,"交换排序"),
                new Question("6.在堆排序和快速排序中，如果记录的关键字近似正序或反序，则优先选用___排序。", null,"堆"),
                new Question("7.归并排序、堆排序和快速排序的平均时间性能相当， 但是___排序方法所需的辅助存储量最多。", null,"归并"),
                new Question("8.在顺序表中，采用从后向前查找时，通常将待查记录存放在顺序表中下标为___的位置，称之为哨兵。", null,"0"),
                new Question("9.折半查找也称为二分查找，它需要满足两个条件分别，即记录是有序的并且采用___存储结构。", null,"顺序"),
                new Question("10.一个顺序存储的有序表为{7， 9，11，30，42，45，52，65，77，89，91}，第一个元素7保存在下标为1的位置，当折半查找89时，___次比较后查找成功。", null,"3")
        };
        return writtenAnswerQuestion;
    }
}
