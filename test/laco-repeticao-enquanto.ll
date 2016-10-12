; ModuleID = 'programa.bc'

declare i32 @escreva(i8*, ...)

declare void @leia(i32, ...)

define void @inicio() {
incio_funcao:
  %contador = alloca i32
  store i32 10, i32* %contador
  br label %enquanto.condicao

enquanto.condicao:                                ; preds = %enquanto.entrada, %incio_funcao
  %contador1 = load i32* %contador
  %0 = icmp sgt i32 %contador1, 7
  br i1 %0, label %enquanto.entrada, label %enquanto.saida

enquanto.entrada:                                 ; preds = %enquanto.condicao
  %contador2 = load i32* %contador
  %1 = sub i32 %contador2, 1
  store i32 %1, i32* %contador
  br label %enquanto.condicao

enquanto.saida:                                   ; preds = %enquanto.condicao
  ret void
}
