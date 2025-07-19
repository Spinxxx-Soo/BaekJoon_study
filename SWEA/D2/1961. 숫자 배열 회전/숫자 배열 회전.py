T = int(input())

for test_case in range(1, T+1):
    N = int(input())
    arr = []
    for i in range(N):# 배열 입력
        a = list(map(int, input().split()))
        arr.append(a)

    #90 회전
    arr90 = [[]*N for _ in range(N)]
    for i in range(N): #열
        for j in range(N-1, -1, -1): #행
            a = arr[j][i] #행변경, 열고정
            arr90[i].append(a)

    # 180 회전
    arr180 = []
    for i in range(N-1, -1, -1):
        a = arr[i][::-1]
        arr180.append(a)

    #270 회전
    arr270 = [[] * N for _ in range(N)]
    for i in range(N-1, -1, -1):  # 열
        for j in range(N):  # 행
            a = arr[j][i]  # 행변경, 열고정
            arr270[i].append(a)
    arr270 = arr270[::-1]

    print(f"#{test_case}")
    for i in range(N):
        print(''.join(map(str,arr90[i])), end=' ')
        print(''.join(map(str,arr180[i])), end=' ')
        print(''.join(map(str, arr270[i])), end='\n')