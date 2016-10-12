; ModuleID = 'programa.bc'

declare i32 @escreva(i8*, ...)

declare void @leia(i32, ...)

define void @inicio() {
incio_funcao:
  %numero = alloca i32
  store i32 2, i32* %numero
}