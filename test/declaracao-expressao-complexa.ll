; ModuleID = 'programa'

@0 = private unnamed_addr constant [3 x i8] c"%d\00"

declare i32 @escreva(i8*, ...)

define i32 @inicio() {
entry:
  %numero = alloca i32
  store i32 -8, i32* %numero
  %numero.carregado = load i32* %numero
  %0 = call i32 (i8*, ...)* @escreva(i8* getelementptr inbounds ([3 x i8]* @0, i32 0, i32 0), i32 %numero.carregado)
  ret i32 0
}
