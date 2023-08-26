from collections import deque
import sys

input = sys.stdin.readline


def bfs(i, j, L):
    def left():
        if board[nx][ny] in [1, 3, 4, 5]:
            q.append([nx, ny, t + 1])
            visited[nx][ny] = 1

    def right():
        if board[nx][ny] in [1, 3, 6, 7]:
            q.append([nx, ny, t + 1])
            visited[nx][ny] = 1

    def up():
        if board[nx][ny] in [1, 2, 5, 6]:
            q.append([nx, ny, t + 1])
            visited[nx][ny] = 1

    def down():
        if board[nx][ny] in [1, 2, 4, 7]:
            q.append([nx, ny, t + 1])
            visited[nx][ny] = 1

    global ans
    q = deque()
    q.append([i, j, 0])
    visited[i][j] = 1

    while q:
        s = q.popleft()
        x = s[0]
        y = s[1]
        t = s[2]
        ans += 1
        if t == L-1:
            continue
        num = board[x][y]
        for i in tunnel[num]:
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < N and 0 <= ny < M:
                if not visited[nx][ny] and board[nx][ny]:
                    if num == 1:
                        if x < nx:  # 다음 파이프가 아래에 있을 때
                            down()
                        elif x > nx:  # 다음 파이프가 위에 있을 때
                            up()

                        elif y > ny:  # 다음 파이프가 왼쪽에 있을 때
                            left()

                        else:  # 다음 파이프가 오른족에 있을 때
                            right()

                    if num == 2:
                        if x < nx:  # 다음 파이프가 아래에 있을 때
                            down()

                        else:  # 다음 파이프가 위에 있을 때
                            up()

                    if num == 3:
                        if y > ny:  # 다음 파이프가 왼쪽에 있을 때
                            left()

                        else:  # 다음 파이프가 오른쪽에 있을 때
                            right()

                    if num == 4:
                        if x > nx:  # 다음 파이프가 위에 있을 때
                            up()

                        else:  # 다음 파이프가 오른쪽에 있을 때
                            right()

                    if num == 5:
                        if x < nx:  # 다음 파이프가 아래에 있을 때
                            down()

                        else:  # 다음 파이프가 오른쪽에 있을 때
                            right()

                    if num == 6:
                        if y > ny:  # 다음 파이프가 왼쪽에 있을 때
                            left()

                        else:  # 다음 파이프가 아래에 있을 때
                            down()

                    if num == 7:
                        if x > nx:  # 다음 파이프가 위에 있을 때
                            up()

                        else:  # 다음 파이프가 왼쪽에 있을 때
                            left()


# 상:0 하:1 좌:2 우:3
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

tunnel = [
    [-1],  # 0
    [0, 1, 2, 3],  # 1  +
    [0, 1],  # 2  |
    [2, 3],  # 3  ㅡ
    [0, 3],  # 4  ㄴ
    [1, 3],  # 5  ⌜
    [1, 2],  # 6  ㄱ
    [0, 2]  # 7  ⌟
]

T = int(input())

for t in range(1, T + 1):
    board = []
    ans = 0
    # 맵의 크기 N, M 맨홀 뚜껑의위치 R,C 탈출 소요 시간 L
    N, M, R, C, L = map(int, input().split())
    visited = [[0] * M for _ in range(N)]
    for _ in range(N):
        board.append(list(map(int, input().split())))

    bfs(R, C, L)

    print("#{} {}".format(t, ans))