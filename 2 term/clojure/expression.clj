(defn unary_operation [function]
      (fn [operation]
          (fn [vars] (function (operation vars)))))
(defn binary_operation [function]
      (fn [operation1, operation2]
          (fn [vars] (function (operation1 vars) (operation2 vars)))))

(defn constant [val] (constantly val))
(defn variable [a] (fn [vars] (vars a)))
(def subtract (binary_operation -))
(def multiply (binary_operation *))
(def divide (binary_operation (fn [a b] (/ a (double b)))))
(def add (binary_operation +))
(def negate (unary_operation -))
(def sin (unary_operation (fn [a] (Math/sin (double a)))))
(def cos (unary_operation (fn [a] (Math/cos a))))

(def operations {'+ add, '- subtract, '* multiply, '/ divide, 'negate negate, 'sin sin, 'cos cos})
(defn parseOperation [exp] (cond
                             (number? exp) (constant exp)
                             (seq? exp) (apply (operations (first exp)) (mapv parseOperation (rest exp)))
                             (symbol? exp) (variable (str exp))))

(defn parseFunction [exp] (parseOperation (read-string exp)))


(defn pget [obj key]
      (cond
        (contains? obj key) (obj key)
        (contains? obj :prototype) (pget (obj :prototype) key)
        :else nil))

(defn pcall [obj key & args]
      (apply (pget obj key) obj args))

(defn field [key]
      #(pget % key))

(defn method  [key]
      #(apply pcall %1 key %&))

(def toString (method :toString))
(def evaluate (method :evaluate))
(def diff (method :diff))

(def Constant)

(def ConstantProto
  (let [number (field :value)]
       {:toString (fn [this]
                      (let [value (number this)] (if (integer? value) (str value) (format "%.1f" value))))
        :evaluate (fn [this _]
                      (number this))
        :diff (fn [arg arg1] (Constant 0))
        }))

(defn Constant [number]
      {:prototype ConstantProto
       :value     number})


(def one (Constant 1))
(def zero (Constant 0))

(defn Variable [identifier]
      (let  [name (field :value)]
            {:value     identifier
             :toString #(name %)
             :evaluate #(%2 (name %1))
             :diff     #(if (= (name %1) %2) (Constant 1) (Constant 0))}))

(def Operation
  (let [operand (field :operand)
        sign (field :sign)
        operation (field :function)
        diffOperation (field :diffOperation)]
       {:toString #(str "(" (sign %) " " (clojure.string/join " " (mapv toString (operand %))) ")")
        :evaluate #(apply (operation %1) (mapv (fn [operand] (evaluate operand %2)) (operand %1)))
        :diff     (fn [this var] (let [curArgs (operand this) a (first curArgs) b (last curArgs)]
                                      (if (= (count curArgs) 2)
                                        ((diffOperation this) a b (diff a var) (diff b var))
                                        ((diffOperation this) a (diff a var)))))}))

(defn newOperation [sign function diffOperation]
      (fn [& operand] {
                       :operand (vec operand)
                       :prototype {
                                   :sign sign
                                   :function function
                                   :diffOperation diffOperation
                                   :prototype Operation
                                   }
                       }))


(def Add (newOperation '+ + (fn [_ _ da db] (Add da db))))

(def Subtract (newOperation '- - (fn [_ _ da db] (Subtract da db))))

(def Multiply (newOperation '* * (fn [a b da db] (Add (Multiply da b) (Multiply db a)))))

(def Divide (newOperation '/ (fn [x y] (/ x (double y)))
                          (fn [a b da db] (Divide (Subtract (Multiply b da)
                                                            (Multiply a db))
                                                  (Multiply b b)))))

(def Negate (newOperation 'negate - (fn [args diffed] (Subtract diffed))))

(declare Sin)
(def Cos (newOperation 'cos (fn [x] (Math/cos x)) (fn [a var] (Negate (Multiply var (Sin a) )))))
(def Sin (newOperation 'sin (fn [x] (Math/sin x)) (fn [a var] (Multiply var (Cos a) ))))


(def objOperations {'+ Add, '- Subtract, '* Multiply, '/ Divide, 'negate Negate, 'sin Sin, 'cos Cos})


(defn parseOperation [exp] (cond
                             (number? exp) (Constant exp)
                             (seq? exp) (apply (objOperations (first exp)) (mapv parseOperation (rest exp)))
                             :else (Variable (str exp))))

(defn parseObject [exp] (parseOperation (read-string exp)))
