; ModuleID = 'programa'

@0 = private unnamed_addr constant [3 x i8] c"%d\00"

declare i32 @escreva(i8*, ...)

declare void @leia(i32, ...)

define void @inicio() {
incio_funcao:
  %numero = alloca i32
  store i32 1, i32* %numero
  %numero1 = load i32* %numero
  %0 = call i32 (i8*, ...)* @escreva(i8* getelementptr inbounds ([3 x i8]* @0, i32 0, i32 0), i32 %numero1)
}