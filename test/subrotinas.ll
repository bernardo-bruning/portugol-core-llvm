; ModuleID = 'programa.bc'

@0 = private unnamed_addr constant [6 x i8] c"teste\00"

declare i32 @escreva(i8*, ...)

declare void @leia(i32, ...)

define i8* @mensagem() {
incio_funcao:
  ret i8* getelementptr inbounds ([6 x i8]* @0, i32 0, i32 0)
}

define void @inicio() {
incio_funcao:
  %0 = call i8* @mensagem()
  %1 = call i32 (i8*, ...)* @escreva(i8* %0)
  ret void
}