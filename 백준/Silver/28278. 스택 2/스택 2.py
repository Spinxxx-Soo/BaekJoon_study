import sys

stack = []

n = int(input())

for i in range(n):
    command = sys.stdin.readline().split()
    
    if command[0] == '1':
        stack.append(command[1])
    elif command[0] == '2':
        if stack:
            print(stack.pop())
        else:
            print(-1)
    elif command[0] == '3':
        print(len(stack))
    elif command[0] == '4':
        if stack:
            print(0)
        else:
            print(1)
    else:
        if stack:
            print(stack[-1]) #append는 뒤로 붙으니까...
        else:
            print(-1)