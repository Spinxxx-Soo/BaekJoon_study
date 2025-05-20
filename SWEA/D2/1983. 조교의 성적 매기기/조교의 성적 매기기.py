T = int(input())
score = ['A+', 'A0', 'A-', 'B+', 'B0', 'B-', 'C+', 'C0', 'C-', 'D0']

for test_case in range(1, T+1):
    N, K = map(int, input().split())
    s_score = {}
    s_aver = {}
    arr = []

    for i in range(1, N+1):
        M, L, R = map(int, input().split())
        arr.append(M*0.35 + L*0.45 + R*0.2)
        s_aver[i] = arr[i-1] # 학생 번호와 총점 등록

    # 점수 높은 순으로 정렬하기
    arr.sort(reverse=True)

    # 점수 등록
    for i in range(0, 10):
        j = 0
        while j <= (N//10-1): # 학점에 해당되는 횟수 반복
            for k, v in s_aver.items():
                if v == arr[j]:
                    s_score[k] = score[i]
                    j += 1
                    if j > (N//10 -1):
                        break

        arr = arr[j:] # 등록한 학점 지우기

    #정답 출력
    print(f"#{test_case} {s_score[K]}")