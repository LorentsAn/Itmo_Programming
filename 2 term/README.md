# Programming paradigms in ITMO

## Домашнее задание 13. Деревья поиска на Prolog
Реализуйте ассоциативный массив (map) на основе деревьев поиска. Для решения можно реализовать любое дерево поиска логарифмической высоты.  
Разработайте правила:  


* map_build(ListMap, TreeMap), строящее дерево из упорядоченного списка пар ключ-значение (O(n));
* map_get(TreeMap, Key, Value), проверяющее, что массив содержит заданную пару ключ-значение (O(log n)).


## Домашнее задание 12. Простые числа на Prolog
Разработайте правила:  

* prime(N), проверяющее, что N – простое число.
* composite(N), проверяющее, что N – составное число.
* prime_divisors(N, Divisors), проверяющее, что список Divisors содержит все простые делители числа N, упорядоченные по возрастанию.  
Если N делится на простое число P несколько раз, то Divisors должен содержать соответствующее число копий P

## Домашнее задание 10. Объектные выражения на Clojure
Разработайте конструкторы Constant, Variable, Add, Subtract, Multiply и Divide для представления выражений с одной переменной.  
Пример описания выражения 2x-3:


```
(def expr
  (Subtract
    (Multiply
      (Constant 2)
      (Variable "x"))
    (Constant 3)))
```


* Функция (evaluate expression vars) должна производить вычисление выражения expression для значений переменных, заданных отображением vars. Например, (evaluate expr {"x" 2}) должно быть равно 1.
* Функция (toString expression) должна выдавать запись выражения в стандартной для Clojure форме.
* Функция (parseObject "expression") должна разбирать выражения, записанные в стандартной для Clojure форме. Например,
 ```
(parseObject "(- (* 2 x) 3)")
``` 
должно быть эквивалентно expr.  
* Функция (diff expression "variable") должена возвращать выражение, представляющее производную исходного выражения по заданой пермененной.  
Например, (diff expression "x") должен возвращать выражение, эквивалентное ```(Constant 2)```, при этом выражения ```(Subtract (Constant 2) (Constant 0)) ```и
```
(Subtract
  (Add
    (Multiply (Constant 0) (Variable "x"))
    (Multiply (Constant 2) (Constant 1)))
  (Constant 0))
```
так же будут считаться правильным ответом.  

## Домашнее задание 9. Функциональные выражения на Clojure
Разработайте функции constant, variable, add, subtract, multiply и divide для представления арифметических выражений.  
Пример описания выражения 2x-3:

```
(def expr
  (subtract
    (multiply
      (constant 2)
      (variable "x"))
    (constant 3)))
```

Выражение должно быть функцией, возвращающей значение выражения при подстановке переменных, заданных отображением. Например, (expr {"x" 2}) должно быть равно 1.  
Разработайте разборщик выражений, читающий выражения в стандартной для Clojure форме. Например,  
```
(parseFunction "(- (* 2 x) 3)")
```
должно быть эквивалентно expr.

## Домашнее задание 8. Линейная алгебра на Clojure
Разработайте функции для работы с объектами линейной алгебры, которые представляются следующим образом:  
* скаляры – числа
* векторы – векторы чисел;
* матрицы – векторы векторов чисел.  
Функции над векторами:
* v+/v-/v*/vd – покоординатное сложение/вычитание/умножение/деление;
* scalar/vect – скалярное/векторное произведение;
* v*s – умножение на скаляр.  
Функции над матрицами:
* m+/m-/m*/md – поэлементное сложение/вычитание/умножение/деление;
* m*s – умножение на скаляр;
* m*v – умножение на вектор;
* m*m – матричное умножение;
* transpose – транспонирование;  

## Домашнее задание 7. Обработка ошибок на JavaScript
Добавьте в предыдущее домашнее задание функцию parsePrefix(string), разбирающую выражения, задаваемые записью вида «(- (* 2 x) 3)».  
Если разбираемое выражение некорректно, метод parsePrefix должен бросать человеко-читаемое сообщение об ошибке.  
Добавьте в предыдущее домашнее задание метод prefix(), выдающий выражение в формате, ожидаемом функцией parsePrefix.  
При выполнение задания следует обратить внимание на:
1) Применение инкапсуляции.
2) Выделение общего кода для операций.
3) Минимизацию необходимой памяти.
4) Обработку ошибок.

## Домашнее задание 6. Объектные выражения на JavaScript
Разработайте классы Const, Variable, Add, Subtract, Multiply, Divide, Negate для представления выражений с одной переменной.  
Пример описания выражения 2x-3:
```
let expr = new Subtract(
    new Multiply(
        new Const(2),
        new Variable("x")
    ),
    new Const(3)
);

println(expr.evaluate(5));
```

При вычислении такого выражения вместо каждой переменной подставляется её значение, переданное в качестве аргумента метода evaluate.  
Таким образом, результатом вычисления приведенного примера должно стать число 7.  
Метод toString() должен выдавать запись выражения в обратной польской записи. Например, expr.toString() должен выдавать «2 x * 3 -».  
При выполнение задания следует обратить внимание на:
1) Применение инкапсуляции.
2) Выделение общего кода для операций.
3) Минимизацию необходимой памяти.

## Домашнее задание 5. Функциональные выражения на JavaScript
Разработайте функции cnst, variable, add, subtract, multiply, divide, negate для вычисления выражений с одной переменной.  
Функции должны позволять производить вычисления вида:
```
let expr = subtract(
    multiply(
        cnst(2),
        variable("x")
    ),
    cnst(3)
);

println(expr(5));
```

При вычислении такого выражения вместо каждой переменной подставляется значение, переданное в качестве параметра функции expr (на данном этапе имена переменных игнорируются).  
Таким образом, результатом вычисления приведенного примера должно стать число 7. Тестовая программа должна вычислять выражение x2−2x+1, для x от 0 до 10.  

## Домашнее задание 4. Вычисление в различных типах
Добавьте в программу разбирающую и вычисляющую выражения трех переменных поддержку вычисления в различных типах.  
  
Создайте класс expression.generic.GenericTabulator, реализующий интерфейс expression.generic.Tabulator:  
```
   public interface Tabulator {
       Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception;
   }
```            
Аргументы
 
* mode — режим работы  
Режим	Тип
* i	int (с детекцией переполнений)
* d	double
* bi	BigInteger
* expression — вычисляемое выражение;
* x1, x2; y1, y2; z1, z2 — диапазоны изменения переменны (включительно).  
Возвращаемое значение — таблица значений функции, где R[i][j][k] соответствует x = x1 + i, y = y1 + j, z = z1 + k. Если вычисление завершилось ошибкой, в соответствующей ячейке должен быть null.  

Доработайте интерфейс командной строки:  
Первым аргументом командной строки программа должна принимать указание на тип, в котором будут производится вычисления:  
Опция	Тип
* -i	int (с детекцией переполнений)
* -d	double
* -bi	BigInteger  
Вторым аргументом командной строки программа должна принимать выражение для вычисления.  
Программа должна выводить результаты вычисления для всех целочисленных значений переменных из диапазона −2..2.  
Реализация не должна содержать непроверяемых преобразований типов.  
Реализация не должна использовать аннотацию @SuppressWarnings.  
При выполнении задания следует обратить внимание на простоту добавления новых типов и операциий.  

## Домашнее задание 3. Очереди
Определите интерфейс очереди Queue и опишите его контракт.  
Реализуйте класс LinkedQueue — очередь на связном списке.  
Выделите общие части классов LinkedQueue и ArrayQueue в базовый класс AbstractQueue.  
Это домашнее задание связанно с предыдущим.  

## Домашнее задание 2. Очередь на массиве
Определите модель и найдите инвариант структуры данных «очередь». Определите функции, которые необходимы для реализации очереди. Найдите их пред- и постусловия, при условии что очередь не содержит null.
Реализуйте классы, представляющие циклическую очередь с применением массива.  
Класс ArrayQueueModule должен реализовывать один экземпляр очереди с использованием переменных класса.  
Класс ArrayQueueADT должен реализовывать очередь в виде абстрактного типа данных (с явной передачей ссылки на экземпляр очереди).  
Класс ArrayQueue должен реализовывать очередь в виде класса (с неявной передачей ссылки на экземпляр очереди).  
Должны быть реализованы следующие функции (процедуры) / методы:  
* enqueue – добавить элемент в очередь;
* element – первый элемент в очереди;
* dequeue – удалить и вернуть первый элемент в очереди;
* size – текущий размер очереди;
* isEmpty – является ли очередь пустой;
* clear – удалить все элементы из очереди.  
Инвариант, пред- и постусловия записываются в исходном коде в виде комментариев.  
Обратите внимание на инкапсуляцию данных и кода во всех трех реализациях.  
Напишите тесты к реализованным классам.  

## Домашнее задание 1. Бинарный поиск
Реализуйте итеративный и рекурсивный варианты бинарного поиска в массиве.  
На вход подается целое число x и массив целых чисел a, отсортированный по невозрастанию. Требуется найти минимальное значение индекса i, при котором a[i] <= x.  
Для функций бинарного поиска и вспомогательных функций должны быть указаны, пред- и постусловия.  
Для реализаций методов должны быть приведены доказательства соблюдения контрактов в терминах троек Хоара.  
Интерфейс программы.  
Имя основного класса — BinarySearch.  
Первый аргумент командной строки — число x.  
Последующие аргументы командной строки — элементы массива a.  
Пример запуска: java BinarySearch 3 5 4 3 2 1. Ожидаемый результат: 2.  
