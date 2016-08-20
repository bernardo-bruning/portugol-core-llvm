; ModuleID = 'programa'

@0 = private unnamed_addr constant [3 x i8] c"%d\00"

declare i32 @escreva(i8*, ...)

define i32 @main(i32) {
entry:
  %1 = call i32 (i8*, ...)* @escreva(i8* getelementptr inbounds ([3 x i8]* @0, i32 0, i32 0), i32 1)
  ret i32 1
}