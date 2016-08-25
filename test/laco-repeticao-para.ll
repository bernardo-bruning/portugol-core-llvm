; ModuleID = 'programa'

@0 = private unnamed_addr constant [7 x i8] c"Teste\0A\00"

declare i32 @escreva(i8*, ...)

define i32 @main(i32) {
entry:
  %contador = alloca i32
  store i32 1, i32* %contador
  br label %para.entrada

para.condicao:                                    ; preds = %para.entrada
  %contador.carregado = load i32* %contador
  %1 = icmp ule i32 %contador.carregado, 10
  br i1 %1, label %para.entrada, label %para.saida

para.entrada:                                     ; preds = %para.condicao, %entry
  %2 = call i32 (i8*, ...)* @escreva(i8* getelementptr inbounds ([7 x i8]* @0, i32 0, i32 0))
  %contador.carregado1 = load i32* %contador
  %3 = add i32 %contador.carregado1, 1
  store i32 %3, i32* %contador
  br label %para.condicao

para.saida:                                       ; preds = %para.condicao
  ret i32 1
}