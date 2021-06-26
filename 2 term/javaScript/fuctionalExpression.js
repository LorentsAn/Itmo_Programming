"use strict";
const cnst = value => (x, y, z) => value
const variable = a =>
    function (x, y, z) {
        if (a === "x") {
            return x;
        }
        if (a === "y") {
            return y;
        }
        if (a === "z") {
            return z;
        }
    };

const UnaryOperation = (f) => (one) => (x, y, z) => f(one(x, y, z));
const negate = UnaryOperation(one => -one);
const cube = UnaryOperation(one => one * one * one);
const cuberoot = UnaryOperation(one => Math.cbrt(one))
const BinaryOperation = (f) => (first, second) => (x, y, z) => f(first(x, y, z), second(x, y, z));
const add = BinaryOperation((first, second) => first + second);
const subtract = BinaryOperation((first, second) => first - second);
const multiply = BinaryOperation((first, second) => first * second);
const divide = BinaryOperation((first, second) => first / second);
