money = 100
from random import randint

while money > 0:
    print('Your money: ', money)
    # this loop is for one debt round
    go_on = False

    while True: # make sure the debt is a possible value
        debt = int(input("please debt: "))
        if 0 < debt <= money:
            break

    first = randint(1, 6) + randint(1, 6)
    print('first points: %d' % first)

    if first == (7 or 11):
        print('You win!')
        money += debt
    elif first == (2 or 3 or 12):
        print('Markers win!')
        money -= debt
    else:
        go_on = True

    while go_on:
        second = randint(1, 6) + randint(1, 6)
        go_on = False
        if second == 7:
            print('Markers win!')
            money -= debt
        elif second == first:
            print('You win!')
            money += debt
        else:
            go_on = True

print('You lose all your money!')