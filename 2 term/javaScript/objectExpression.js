"use script"

function BinOper(operation, f, a, b) {
    this.operation = operation;
    this.f = f;
    this.a = a;
    this.b = b;
}

BinOper.prototype.evaluate = function (x, y, z) {
    return this.f(this.a.evaluate(x, y, z), this.b.evaluate(x, y, z))
}
BinOper.prototype.toString = function () {
    return this.a.toString() + " " + this.b.toString() + " " + this.operation;
}
BinOper.prototype.prefix = function () {
    return "(" + this.operation + " " + this.a.prefix() + " " + this.b.prefix() + ")";
}

function parseToTwo(operation, f) {
    return function (a, b) {
        return new BinOper(operation, f, a, b)
    }
}

const Add = parseToTwo("+", (a, b) => a + b);
const Subtract = parseToTwo("-", (a, b) => a - b);
const Multiply = parseToTwo("*", (a, b) => a * b);
const Divide = parseToTwo("/", (a, b) => a / b);
const ArcTan2 = parseToTwo("atan2", (a, b) => Math.atan2(a, b))

function UnarOperation(operation, a, f) {
    this.operation = operation;
    this.f = f;
    this.a = a;
}

UnarOperation.prototype.evaluate = function (x, y, z) {
    return this.f(this.a.evaluate(x, y, z))
}
UnarOperation.prototype.toString = function () {
    return this.a.toString() + " " + this.operation;
}
UnarOperation.prototype.prefix = function () {
    return "(" + this.operation + " " + this.a.prefix() + ")";
}

function parseOne(operation, f) {
    return function (a) {
        return new UnarOperation(operation, a, f)
    }
}

const ArcTan = parseOne("atan", (a => Math.atan(a)))
const Negate = parseOne("negate", (a => -a))
const Sinh = parseOne("sinh", a => Math.sinh(a))
const Cosh = parseOne("cosh", a => Math.cosh(a))

function Const(a) {
    this.a = a;
}

Const.prototype.evaluate = function (x, y, z) {
    return this.a;
}
Const.prototype.toString = function () {
    return this.a.toString();
}
Const.prototype.prefix = function () {
    return this.a.toString();
}

function Variable(a) {
    this.a = a;
}

Variable.prototype.evaluate = function (x, y, z) {
    if (this.a === "x") {
        return x;
    }
    if (this.a === "y") {
        return y;
    }
    if (this.a === "z") {
        return z;
    }
}

Variable.prototype.toString = function () {
    return this.a;
}

Variable.prototype.prefix = function () {
    return this.a;
}

all_unary_operations = {
    "negate": Negate,
    "cosh": Cosh,
    "sinh": Sinh
}

all_binary_operations = {
    "+": Add,
    "-": Subtract,
    "*": Multiply,
    "/": Divide
}

function parsePrefix(expression) {
    let balance = 0;
    let stack = [];
    let pos = 0;
    let brackets = false;
    var foundOper = false;
    const variable = ["x", "y", "z"]


    while (pos < expression.length) {

        function isDigit(letter) {
            return '0' <= letter && letter <= '9';
        }

        if (expression[pos] === ' ') {
            pos += 1;
            continue;
        }

        if (expression[pos] === '(') {
            pos += 1;
            balance += 1;
            brackets = true;

        } else if (expression[pos] === ')') {
            pos += 1;
            balance -= 1;
            if (balance < 0) {
                throw new ParseError("Закрывающих скобок больше чем открывающих в позиции " + pos);
            }
        } else if (variable.indexOf(expression[pos]) !== -1) {
            stack.push(new Variable(expression[pos]));
            pos += 1;
        } else if ((isDigit(expression[pos])) ||
            (expression[pos] === '-' && pos + 1 < expression.length && isDigit(expression[pos + 1]))) {
            let end = pos + 1;
            while (end < expression.length && isDigit(expression[end])) {
                end += 1;
            }
            stack.push(new Const(parseInt(expression.substring(pos, end))));
            pos = end;
        } else {
            let found = false;
            function findOperation (all_operation) {
                for (const raw_op in all_operation) {
                    if (expression.startsWith(raw_op, pos)) {
                        stack.push(raw_op);
                        pos += raw_op.length;
                        found = true;
                        break;
                    }
                }
            }
            findOperation(all_unary_operations);
            if (!found) {
                findOperation(all_binary_operations);
                if (!found) {
                    throw new ParseError('Неожиданный символ в позиции ' + pos)
                }
            }
            foundOper = found;
        }
        while (stack.length > 1) {
            if (stack.length > 2) {
                if (stack[stack.length - 3] in all_binary_operations &&
                    typeof (stack[stack.length - 2]) === 'object' &&
                    typeof (stack[stack.length - 1]) === 'object') {
                    let o1 = stack.pop();
                    let o2 = stack.pop();
                    let f1 = all_binary_operations[stack.pop()];
                    stack.push(new f1(o2, o1));
                    continue;
                }
            }
            if (stack[stack.length - 2] in all_unary_operations &&
                typeof (stack[stack.length - 1]) === 'object') {
                let o1 = stack.pop();
                let f1 = all_unary_operations[stack.pop()];
                stack.push(new f1(o1));
                continue;
            }
            break;
        }
    }
    if (balance !== 0) {
        throw new ParseError('Баланс скобок в конце не 0');
    }
    let Undefined = ['+', '-', '*', '/', "negate", "sinh", "cosh"]

    if (stack.length === 1 && Undefined.indexOf(stack[0]) === -1) {
        if (brackets === true && foundOper === true) {
            return stack[0]
        } else if (brackets === false) {
            return stack[0];
        } else {
            throw new ParseError("Недостаточно аргументов в выражении " + expression);
        }
    } else {
        throw new ParseError("Неправильно введенные операторы в выражении " + expression);
    }
}


function ParseError(message) {
    Error.call(message)
    this.message = message;
}

ParseError.prototype = Object.create(Error.prototype)