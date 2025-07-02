n = int(input())
arr = []

for i in range(n+1):
    s,num = i,i
    while num > 0:
        s = s + (num%10)
        num = (num//10)
    if s == n:
        arr.append(i)

if len(arr) == 0:
    print(0)
else:
    print(arr[0])