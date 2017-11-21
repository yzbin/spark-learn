package com.robin.learn.function

/**
  * 求值策略
  * scala里有两种求职策略(Evaluation Strategy)
  * Call by Value - 对函数实参求值,且仅求一次
  * Call by Name  - 函数实参每次在函数体内被用到都会求值
  *
  * Scala 通常使用Call by Value
  * 如果函数形参类型以 => 开头，那么会使用Call by value
  * def foo(x:Int) = x //call by value
  * def foo(x: => Int) = x //call by name
  */
object Demo1 {
  def main(args: Array[String]): Unit = {
    def bar(x:Int , y: => Int):Int = 1   //x call by value , y call by value
    def loop(): Int = loop
//    print(bar(1, loop))
    print(bar(loop, 1))
  }
}
