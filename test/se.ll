; ModuleID = 'programa'

@0 = private unnamed_addr constant [12 x i8] c"Acessou se!\00"
@1 = private unnamed_addr constant [15 x i8] c"Acessou senao!\00"

declare i32 @escreva(i8*, ...)

define i32 @inicio() {
entry:
  br i1 false, label %se, label %senao
  br label %se

se:                                               ; preds = %entry, %entry
  %1 = call i32 (i8*, ...)* @escreva(i8* getelementptr inbounds ([12 x i8]* @0, i32 0, i32 0))
  br label %saida

senao:                                            ; preds = %entry
  %2 = call i32 (i8*, ...)* @escreva(i8* getelementptr inbounds ([15 x i8]* @1, i32 0, i32 0))
  br label %saida

saida:                                            ; preds = %senao, %se
  ret i32 0
}
