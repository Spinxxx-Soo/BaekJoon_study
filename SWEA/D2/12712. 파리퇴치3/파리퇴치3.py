"""
저장해야하는 데이터: 비선형 구조 -> 파리 좌표값(2차원 배열), 선형 -> 파리 합(최적화?-갱신ormax함수)
자료 구조 : 2d 배열 + 방향 벡터 (그래프X -> 탐색이 아닌 패턴 합 계산 이기에 좌표만 산술하면 됨.)
알고리즘 : 부르트 포스(완전 탐색)
>> 방향 벡터를 사용해보기

1. 입력받은 N으로 중심값을 찾음.
2. 중심값을 중심으로 M에 따른 좌표 규칙 찾기.
>> 방향 벡터 사용!!
3. +와 x 모양 계산
4. 최대 값 출력.
"""

T = int(input())
dir_plus = [(0,1),(0,-1),(-1,0),(1,0)] #+ 모양 방향벡터
dir_x = [(1,1),(1,-1),(-1,-1),(-1,1)] #x 모양 방향벡터

for test_case in range(T):
    arr = list(map(int,input().split()))
    N, M = arr[0], arr[1]

    #칸 수 만큼 입력 받기 fly가 안으로 들어와야함.
    fly = [list(map(int, input().split())) for _ in range(N)]
    answer = 0

    #중심값을 중심으로 파리 계산하기 >> 아님. 그냥 움직이면서 제일 파리를 많이 잡는 경우의 수를 구하는 문제!
    # 중심값이 옮겨지면서 계산 할 수 있게 세팅해야함.
    for r in range(N):
        for c in range(N):

            # + 패턴
            s_plus = fly[r][c] #합계의 초기값 세팅
            for dr, dc in dir_plus: #방향 1개씩 꺼내 오기
                for k in range(1,M):
                    nr = r + dr*k #중심좌표 + r 좌표 
                    nc = c + dc*k #중심좌표 + c 좌표
                    if 0 <= nr < N and 0 <= nc < N: #좌표가 수평선 안에 있을시에만 계산
                        s_plus += fly[nr][nc]
            
            # x 패턴
            s_x = fly[r][c]
            for dr, dc in dir_x:
                for k in range(1,M):
                    nr = r + dr*k
                    nc = c + dc*k
                    if 0 <= nr <N and 0 <= nc < N:
                        s_x += fly[nr][nc]
            
            #최대 값 갱신
            answer = max (s_plus, s_x, answer)

    print(f"#{test_case +1} {answer}")