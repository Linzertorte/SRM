class ConductorWalk:
    def getObserverRecords(self, orderSeat, orderTime, inspectorSeat):
        current = 0
        book = []
        n = len(orderSeat)
        for i in xrange(n):
            if current<orderTime[i]:
                current = orderTime[i]

            if inspectorSeat<=orderSeat[i]:
                book.append(current+inspectorSeat)
                if inspectorSeat!=orderSeat[i]:
                    book.append(current+2*orderSeat[i]-inspectorSeat)

            current += 2*orderSeat[i]

        return tuple(book)
