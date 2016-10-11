; ModuleID = 'programa'

@numero_global = global i32 5
@real_global = global double 1.800000e-01
@0 = private unnamed_addr constant [3 x i8] c"%d\00"
@1 = private unnamed_addr constant [3 x i8] c"%d\00"
@2 = private unnamed_addr constant [3 x i8] c"%d\00"
@3 = private unnamed_addr constant [3 x i8] c"%d\00"

declare i32 @escreva(i8*, ...)

declare void @leia(i32, ...)

define void @inicio() {
incio_funcao:
  %numero_global = load i32* @numero_global
  %0 = add i32 10, %numero_global
  store i32 %0, i32* null
  %numero = load i32* null
  %1 = call i32 (i8*, ...)* @escreva(i8* getelementptr inbounds ([3 x i8]* @0, i32 0, i32 0), i32 %numero)
  %numero_global1 = load i32* @numero_global
  %2 = call i32 (i8*, ...)* @escreva(i8* getelementptr inbounds ([3 x i8]* @1, i32 0, i32 0), i32 %numero_global1)
  %numero_global2 = load i32* @numero_global
  %3 = add i32 %numero_global2, 10
  store i32 %3, i32* @numero_global
  call void @segunda_operacao()
}

define void @segunda_operacao() {
incio_funcao:
  %numero_global = load i32* @numero_global
  %0 = call i32 (i8*, ...)* @escreva(i8* getelementptr inbounds ([3 x i8]* @2, i32 0, i32 0), i32 %numero_global)
  store i32 4, i32* null
  %numero_global1 = load i32* @numero_global
  %numero = load i32* null
  %1 = add i32 %numero_global1, %numero
  store i32 %1, i32* @numero_global
  %numero_global2 = load i32* @numero_global
  %2 = call i32 (i8*, ...)* @escreva(i8* getelementptr inbounds ([3 x i8]* @3, i32 0, i32 0), i32 %numero_global2)
}