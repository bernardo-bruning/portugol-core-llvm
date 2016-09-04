; ModuleID = 'programa'

@0 = private unnamed_addr constant [12 x i8] c"Ol\C3\A1 mundo!\00"

declare i32 @escreva(i8*, ...)

define i32 @main(i32) {
entry:
  %1 = call i32 (i8*, ...)* @escreva(i8* getelementptr inbounds ([12 x i8]* @0, i32 0, i32 0))
  ret i32 0
}