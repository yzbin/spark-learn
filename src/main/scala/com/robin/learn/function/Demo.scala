package com.robin.learn.function
/***
  * 函数定义
1、规规矩矩的写法，带有等号、大括号和返回值类型的形式
def myFunc(var p1 : Int) : Int = {
    //something
}
def myFunc(var p1 : Int) : Unit = {
    //something
}
2、非unit返回值的情形下，省略返回值，让程序根据代码块，自行判断。注意，这里等号还是要的
def myFunc(var p1 : Int) = {
    //something
}
3、unit返回值的情况下，直接省略返回值类型和等号
def myFunc(var p1 : Int) {
    //something
    // return unit
}
4、函数只有一行的情形下省略返回值和大括号
def max2(x: Int, y: Int) = if (x > y) x else y
def greet() = println("Hello, world!")
  */

object Demo {
  def main(args: Array[String]): Unit = {
    // The first
    def myfun1():Unit={
      println("hello scala function1")
    }
    myfun1()

    //The second

    def myfun2()={
      println("hello scala function2")
    }
    myfun2()

    //The third
    def myfun3(){
      println("hello scala function3")
      return Unit
    }
    myfun3()

    //The fourth
    def myfun4(x:Int,y:Int)=if(x>y)x else y

    println(myfun4(3,5))

    //The

  }
}
