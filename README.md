
# 🍩 Donut Delight Ordering System

A Java OOP + File IO Capstone Project


## 📌 Overview
**Donut Delight** is a fully object-oriented Java application designed to take customer donut orders, calculate pricing (including premium toppings and specialty items), generate receipts, and log all orders into a ledger system.

    This project builds on concepts from previous workbooks and workshops:

    Advanced OOP (Fluent interfaces, inheritance, polymorphism, enums)

    GymLedger (ledger logging, CSV file writing, separation of concerns)

    SandwichShop (interactive menu + build-your-own item flows)

    IO Workshop (try-with-resources, BufferedWriter, directory creation)
## 🎯 Features
1. ✔ **Build-Your-Own Donuts**

    -Customers can select:

        Dough type

        Coating

        Pack size (3, 6, 9, 12)

        Multiple toppings

        Multiple drizzles

        Optional extra-toppings surcharge
            - Live previews
            - Fullscreen mode
            - Cross platform
2. ✔ **Premium Toppings**

    -Some toppings cost an extra $1.00.

        Premium items are automatically detected and priced using:

3. ✔ **Specialty Donuts (Fixed-Price)**

     -Two signature creations:

        Maple Bacon Crunch — $4.50

        Cookies & Creme Dream — $4.50

    -These use a SpecialtyDonut subclass with a fixed price and factory methods.

4. ✔ **Signature Donuts (Preset Regular Donuts)**

    -A set of curated donuts built from the regular Donut builder.

5. ✔ **Drinks with Sizes**

    -Fountain Drinks: Small, Medium, Large

    -Lemonades: Medium, Large

        Other drinks default to medium

    -Size-aware pricing added to Pricing.drink(Drink, DrinkSize)

6. ✔ **Snack Deal**

    -Adds a bundled snack charge and optionally includes a drink.

7. ✔ **Receipt System**

    -A formatted .txt receipt saved to a /receipts directory using:

8. ✔ **Ledger Logging**

    -Each order is appended to:

    

    with timestamp, total, donut count, drink, drink size, and snackDeal flag.


## 🧠 OOP Concepts Used
    1. Encapsulation

        -rder model hides interior lists with:

    2. Inheritance

        -SpecialtyDonut extends Donut

    3. Polymorphism

        -Donut and SpecialtyDonut share price() but work differently.
        
    4. Enums

        -All customer options (dough, coatings, toppings, sizes) are type-safe
        enums instead of Strings.

    5. Fluent Builder Interface

        -Donut creation uses chained methods:


## 🔥 Hardest Part of the Code (Reflection Section)
## A Glimpse Into my Notetaking Process


## Run Locally

1. Clone the project

```bash
git clone https://github.com/cealyssa814/DonutDelight
```

Go to the project directory

2. Open in IntelliJ:

    -File → Open → select the project root

    -Make sure SDK = Java 17+
3. Run Main

## UML Diagram

    




## Optimizations

1. Specialty Donuts (with extra charge)

2. Premium Toppings (extra 1$1.00)

3. Color/Background 

4. Enumerations

