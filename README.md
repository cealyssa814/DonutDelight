
<img width="1024" height="1536" alt="Donut Delight Menu" src="https://github.com/user-attachments/assets/a3240f7a-eb6f-4f1f-8c76-0e43e0593cba" />


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

<img width="492" height="155" alt="Screenshot 2025-11-13 at 10 28 18 PM" src="https://github.com/user-attachments/assets/adedc056-f4a9-4ee8-9839-4c8e94cd44a8" />

1. ✔ **Build-Your-Own Donuts**
   
<img width="565" height="220" alt="Screenshot 2025-11-13 at 10 28 37 PM" src="https://github.com/user-attachments/assets/8bdf9b50-2000-4346-b8f1-9e01bcdbecf3" />


    -Customers can select:

 <img width="863" height="111" alt="Screenshot 2025-11-13 at 10 33 08 PM" src="https://github.com/user-attachments/assets/0a156062-c7d2-4945-a102-0a9924b4f06e" />

        Dough type

  <img width="163" height="129" alt="Screenshot 2025-11-13 at 10 33 21 PM" src="https://github.com/user-attachments/assets/80a4a85e-5061-456b-87ae-be796020b9ab" />


        Coating
<img width="181" height="199" alt="Screenshot 2025-11-13 at 10 34 07 PM" src="https://github.com/user-attachments/assets/cab71420-04f0-41a7-ac87-a92156182b88" />


        Pack size (3, 6, 9, 12)

 <img width="196" height="133" alt="Screenshot 2025-11-13 at 10 34 21 PM" src="https://github.com/user-attachments/assets/40bd1deb-71a7-4888-811e-a6acbd7db0ca" />


        Multiple toppings

<img width="471" height="225" alt="Screenshot 2025-11-13 at 10 34 36 PM" src="https://github.com/user-attachments/assets/8a6a2f66-0d21-43b1-88fa-052c48b39bd1" />


        Multiple drizzles

<img width="468" height="269" alt="Screenshot 2025-11-13 at 10 35 01 PM" src="https://github.com/user-attachments/assets/b1864520-98d9-440d-8521-29db2da6386e" />


        Optional extra-toppings surcharge

<img width="290" height="29" alt="Screenshot 2025-11-13 at 10 35 19 PM" src="https://github.com/user-attachments/assets/662cb5f1-0a20-4f8c-a6f3-9e8ce92c5084" />

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

<img width="429" height="283" alt="Screenshot 2025-11-13 at 10 44 30 PM" src="https://github.com/user-attachments/assets/8bec6c6b-c8ab-4673-a91a-7812203e5478" />


    -These use a SpecialtyDonut subclass with a fixed price and factory methods.


4. ✔ **Drinks with Sizes**

<img width="864" height="57" alt="Screenshot 2025-11-13 at 10 43 13 PM" src="https://github.com/user-attachments/assets/38d0fd75-2b91-4237-bfd2-d82aad9a1bc5" />

<img width="222" height="286" alt="Screenshot 2025-11-13 at 10 43 42 PM" src="https://github.com/user-attachments/assets/fe31fc5e-05c5-4e19-90f7-240e4f136f32" />



    -Fountain Drinks: Small, Medium, Large

    -Lemonades: Medium, Large

        Other drinks default to medium

    -Size-aware pricing added to Pricing.drink(Drink, DrinkSize)

6. ✔ **Snack Deal**

    -Adds a bundled snack charge and optionally includes a drink.

7. ✔ **Receipt System**

    -A formatted .txt receipt saved to a /receipts directory using:

<img width="295" height="366" alt="Screenshot 2025-11-13 at 10 49 08 PM" src="https://github.com/user-attachments/assets/34918f00-1510-4e6e-90fb-9ec8bccddd59" />


9. ✔ **Ledger Logging**

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

<img width="760" height="902" alt="Screenshot 2025-11-13 at 10 57 46 PM" src="https://github.com/user-attachments/assets/a142d733-2aa0-444a-ae5d-19f4cc037aa2" />


## A Glimpse Into the beginning stages of my Notetaking Process

![LTCA-33](https://github.com/user-attachments/assets/34b7ec95-26fc-475a-853b-48c0069dd297)

![LTCA-34](https://github.com/user-attachments/assets/f5426db7-934f-405b-982a-68a9524ec564)



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

![UML Diagram-6](https://github.com/user-attachments/assets/fddbb02c-a535-4209-8d39-b3ee2d32aa5a)

## Interesting Piece Of Code

    ThemedPrinter using ConsoleColors 

<img width="1710" height="1107" alt="Screenshot 2025-11-14 at 1 33 14 AM" src="https://github.com/user-attachments/assets/5c0a2ebc-9ab0-4be0-8a4e-1fd97fc45dad" />

<img width="669" height="961" alt="Screenshot 2025-11-14 at 1 34 16 AM" src="https://github.com/user-attachments/assets/7505fa5d-6721-47fc-8762-5072a781c7fe" />


## Optimizations

1. Specialty Donuts (with extra charge)

2. Premium Toppings (extra 1$1.00)

3. Color/Background 

4. Enumerations

