; ModuleID = 'programa.bc'

declare i32 @escreva(i8*, ...)

declare void @leia(i32, ...)

define void @inicio() {
incio_funcao:
  %contador = alloca i32
  store i32 0, i32* %contador
  br label %enquanto.entrada

enquanto.condicao:                                ; preds = %enquanto.entrada
  %contador1 = load i32* %contador
  %0 = icmp ult i32 %contador1, 10
  br i1 %0, label %enquanto.entrada, label %enquanto.saida

enquanto.entrada:                                 ; preds = %enquanto.condicao, %incio_funcao
  %contador2 = load i32* %contador
  %1 = add i32 %contador2, 1
  store i32 %1, i32* %contador
  br label %enquanto.condicao

enquanto.saida:                                   ; preds = %enquanto.condicao
}