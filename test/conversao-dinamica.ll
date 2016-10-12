; ModuleID = 'programa'

@0 = private unnamed_addr constant [3 x i8] c"%d\00"

declare i32 @escreva(i8*, ...)

declare void @leia(i32, ...)

define void @dois_param(double, i32) {
incio_funcao:
}

define void @teste(double) {
incio_funcao:
  %t = alloca i32
  %1 = fptoui double %0 to i32
  store i32 %1, i32* %t
  %t1 = load i32* %t
  %2 = call i32 (i8*, ...)* @escreva(i8* getelementptr inbounds ([3 x i8]* @0, i32 0, i32 0), i32 %t1)
}

define void @inicio() {
incio_funcao:
  %numero = alloca i32
  store i32 2, i32* %numero
  %numero1 = load i32* %numero
  %0 = uitofp i32 %numero1 to double
  call void @teste(double %0)
  %numero2 = load i32* %numero
  %1 = uitofp i32 %numero2 to double
  %numero3 = load i32* %numero
  call void @dois_param(double %1, i32 %numero3)
}