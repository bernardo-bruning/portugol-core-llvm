; ModuleID = 'programa'

@0 = private unnamed_addr constant [3 x i8] c"%d\00"

declare i32 @escreva(i8*, ...)

define i32 @main(i32) {
entry:
  %teste = alloca i1
  store i1 false, i1* %teste
  %teste.carregado = load i1* %teste
  %1 = call i32 (i8*, ...)* @escreva(i8* getelementptr inbounds ([3 x i8]* @0, i32 0, i32 0), i1 %teste.carregado)
  ret i32 1
}