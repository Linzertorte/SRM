class TallPeople:
    def getPeople(self, people):
        people = list(people)
        n = len(people)
        for i in xrange(len(people)):
            people[i] = map(int, people[i].split(' '))
        rows = [0] * n
        for i in xrange(n):
            rows[i] = min(people[i])

        m = len(people[0])

        columns = [None] * m
        for i in xrange(n):
            for j in xrange(m):
                if columns[j] is None or people[i][j] > columns[j]:
                    columns[j] = people[i][j]
        return (max(rows), min(columns))
