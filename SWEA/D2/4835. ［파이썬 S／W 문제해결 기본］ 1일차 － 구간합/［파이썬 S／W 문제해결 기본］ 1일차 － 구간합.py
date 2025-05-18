T = int(input())
for i in range(1, T+1):
    N,M = map(int, input().split())
    arr = list(map(int, input().split()))
    max_sum = max(arr)
    min_sum = sum(arr[0:M])#작은 수를 줄 경우 찾을 수 없음, 따라서 첫번째 연산값을 넣기
    for j in range(N-M+1): #작은 수, 큰 수 찾기
        if max_sum < sum(arr[j:j+M]):
            max_sum = sum(arr[j:j+M])
        if min_sum > sum(arr[j:j+M]):
            min_sum = sum(arr[j:j+M])

    result = max_sum - min_sum
    print(f"#{i} {result}")