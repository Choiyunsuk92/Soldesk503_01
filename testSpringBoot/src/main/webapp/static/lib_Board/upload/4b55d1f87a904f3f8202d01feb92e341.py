import numpy as np
print(np.__version__)


l2 = list(range(10))
print(l2)

l3 = [c for c in l2]
print(l3)

l3 = [True, "2", 3.0, 4]
print([type(item) for item in l3])

print(np.array([1,2,3,4,5,6]))

print(np.array([3.14,4,2,3]))

print(np.array([1,2,3,4], dtype='float32'))


arr = np.array([range(i, i + 3) for i in [2,3,4]])
print(arr)

app = np.zeros(10, dtype=int)
print(app)