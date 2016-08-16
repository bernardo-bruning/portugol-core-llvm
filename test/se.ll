@.str = private unnamed_addr constant [12 x i8] c"Acessou se!\00"
@.str.1 = private unnamed_addr constant [14 x i8] c"Acessou senao\00"

; ModuleID = 'programa'
define i32 @main(i32) {
entry:
  br i1 icmp slt (i32 7, i32 6), label %3, label %5

; <label>:3:                                      ; preds = %2
  %4 = tail call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([12 x i8], [12 x i8]* @.str, i64 0, i64 0))
  br label %7

; <label>:5:                                      ; preds = %2
  %6 = tail call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([14 x i8], [14 x i8]* @.str.1, i64 0, i64 0))
  br label %7

; <label>:7:                                      ; preds = %5, %3
  ret i32 0
  call i32 @escreva_int(i32 %numero)
  ret i32 1
}
