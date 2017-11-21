package com.robin.learn.function

/**
  * 函数是第一等公民
  * scala语言支持:
  * 1.把函数作为实参传递给另外一个函数
  * 2.把函数作为返回值
  * 3.把函数赋值给变量
  * 4.把函数存储在数据结构中
  *
  * 在Scala中，函数就像普通变量一样，同样也具有函数的类型
  * 在Scala语言中，函数类型的格式 A => B ,表示一个接受类型A的参数,并返回类型B的函数
  * 例子： Int => String 是把整型映射为字符串的函数类型
  *
  */
object Demo2 {
  def main(args: Array[String]): Unit = {

    /**
      *  高阶函数
      *  用函数作为形参或返回值的函数，称为高阶函数
      * @param f
      * @return
      */
    def operate(f:(Int, Int) => Int) = {
      f(4, 4)
    }
    /**
      * 匿名函数(Anonymous Function),就是函数常量,也称为函数文字量(Function Literal)
      * 在Scala里，匿名函数的定义格式为
      *     (形式参数) => {函数体}
      * (x:Int) => x*x
      * (x:Int, y:Int) => x+y
      * var add = (x:Int, y:Int) => x+y //add 是一个具有函数类型的变量
      */

//    def greeting() = (name:String) => {"hello"+" "+name}
//    println(greeting()("123"))


//    def tr(x:Int, y:Int)=x + y
//    println(operate(tr))
//    println(add(1, 2))

//    def greeting()=(name:String) => {s"Hello $name"}
//    println(greeting()("world"))

//    def greeting(age:Int)=(name:String)=>{s"Hello $name,your age is $age"}
//    print(greeting(23)("Flygar"))

    //((Int)=>String)=>String
    def convertIntToString(f:(Int)=>String)=f(4)

   //函数定义
    def func(x:Int):String=x+"s"
    //内部调用匿名函数
    println(convertIntToString((x:Int)=>x+"s"))

    println(convertIntToString(func))

    //高阶函数可以产生新的函数，即函数的返回值是一个函数
    def mulitplyBy(factor:Double)=(x:Double)=>factor*x
    println(mulitplyBy(10))
    println(mulitplyBy(10)(50))

  }
}
