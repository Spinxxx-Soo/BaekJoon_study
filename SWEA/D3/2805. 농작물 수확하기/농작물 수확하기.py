T = int(input())

for test_case in range(1,T+1):
    N = int(input())
    farm = []
    sell = 0 # 0개서 시작.
    for i in range(N):
        f = input()
        farm.append(f) # 농장 상황
     
    s = N//2
    t = 1
    for i in range(N//2+1, N):
        if i == N//2+1:
            for j in range(N//2+1): # 피라미드 수확. - 슬라이싱 방법: str 슬라이싱을 int로 변환 후 1개씩 카운트.
                for a in range(s, s+t):
                    num = farm[j]
                    sell += int(num[a])
                if j == N//2:
                    break
                s -=1 #start 포인트는 왼쪽으로 1칸씩 이동.
                t +=2 #tep 포인트는 2칸씩 증가.
        # 역피라미드 수확. 중간 다음 줄부터 시작.
        s += 1 #start 포인트는 오른쪽으로 1칸씩 이동.
        t -= 2 #tep 포인트는 2칸씩 감소...
        for a in range(s, s + t):
            num = farm[i]
            sell += int(num[a])
    if N == 1:
        print(f"#{test_case} {f}")
    else:
        print(f"#{test_case} {sell}")