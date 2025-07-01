n, m = map(int, input().split())
num = list(map(int, input().split()))
best = 0

for i in range(n):
    for j in range(i+1, n):
        for k in range(j+1, n): #k는 j 다음 숫자여야 해서 i+2는 안됨. j+1만 가능함.
            s = num[i] + num[j] + num[k]
            
            if s > m :
                continue
            else:
                best = max(s, best)

print(best)