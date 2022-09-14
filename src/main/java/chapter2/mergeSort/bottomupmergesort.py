def bottomUpMergeSort(anArray):
    aux = anArray.copy()
    size = 1
    while size < len(anArray):
        lo = 0
        while lo < len(anArray) - size:
            hi = min(lo + 2*size - 1, len(anArray) - 1)
            med = lo + size -1
            merge(lo, med, hi, anArray, aux)
            lo += 2 * size
        size = 2 * size


def merge(lo, med, hi, array, copy):
    idxFirst = lo
    idxSecond = med + 1
    current = lo

    for i in range(lo, hi + 1):
        copy[i] = array[i]

    while current <= hi:
        if idxFirst > med:
            array[current] = copy[idxSecond]
            idxSecond +=1
        elif idxSecond > hi:
            array[current] = copy[idxFirst]
            idxFirst +=1
        elif copy[idxFirst] < copy[idxSecond]:
            array[current] = copy[idxFirst]
            idxFirst +=1
        else:
            array[current] = copy[idxSecond]
            idxSecond +=1
        current+=1
        
