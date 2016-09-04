; ModuleID = 'programa'
@0 = private unnamed_addr constant [7 x i8] c"Teste\0A\00"
declare i32 @escreva(i8*, ...)
define i32 @main(i32) {
entry:
  %contador = alloca i32
  store i32 0, i32* %contador
  br label %enquanto.entrada
enquanto.condicao:                                ; preds = %enquanto.entrada
  %contador.carregado = load i32* %contador
  %1 = icmp ult i32 %contador.carregado, 10
  br i1 %1, label %enquanto.entrada, label %enquanto.saida
enquanto.entrada:                                 ; preds = %enquanto.condicao, %entry
  %contador.carregado1 = load i32* %contador
  %2 = add i32 %contador.carregado1, 1
  store i32 %2, i32* %contador
  %3 = call i32 (i8*, ...)* @escreva(i8* getelementptr inbounds ([7 x i8]* @0, i32 0, i32 0))
  br label %enquanto.condicao
enquanto.saida:                                   ; preds = %enquanto.condicao
  ret i32 0
}