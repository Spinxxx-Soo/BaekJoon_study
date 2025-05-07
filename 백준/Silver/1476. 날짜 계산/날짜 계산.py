# 1476 날짜 계산
# 1<E<15,<14> 1<S<28,<9> 1<M<19 주기-665 / 312
# 312//15 = 20..12 E=12 
# 312//28 = 11..4 S=4
# 312//19 = 16..8 M=8

e, s, m = map(int, input().split())
year = 1

while True:
    if (year - 1) % 15 + 1 == e and (year - 1) % 28 + 1 == s and (year - 1) % 19 + 1 == m:
        break
    year += 1

print(year)