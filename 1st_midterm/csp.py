from constraint import *

# vo dve HALL
# vo each hall -> category, hall
"""Constrains
1. razlika od dva casa megju natprevarite
2. vo ista kategorija ne smeat u isti cas
3. za category 1 vo prva sala 

"""


def hall1_constraint(a):
    # Hall_2_Day_4_16
    _, x, _, _, _ = a.split("_")
    return x == "1"


def three_hours(input1, input2):
    # Hall_2_Day_4_16
    if input1.split("_")[3] == input2.split("_")[3]:
        return abs(int(input1.split("_")[4]) - int(input2.split("_")[4])) > 3

    return True


if __name__ == '__main__':
    problem = Problem(BacktrackingSolver())
    num_competitions = int(input())
    matches = dict()
    # match 1 cat1
    for _ in range(num_competitions):
        key = input().split(" ")
        # match1 category
        matches[key[0]] = key[1]

    # print(matches)
    days = int(input())
    match_variables = [m for m in matches.keys()]
    termini_variables = list(range(9, 17))
    hall_variables = ["Hall_1", "Hall_2"]
    day_variables = [f"Day_{i}" for i in range(1, days + 1)]

    # promenlivi
    domain = []

    for h in hall_variables:
        for d in day_variables:
            for t in termini_variables:
                domain.append(f"{h}_{d}_{t}")

    # vo key imam match1 match2.... i matches[key] ke mi isprinta category
    # odnosno
    # 'M1': 'cat1'

    for key in match_variables:
        # print(matches[key])

        category = matches[key]
        if category == "cat1":
            problem.addConstraint(hall1_constraint, [key])
        problem.addVariable(key, domain)

    problem.addConstraint(AllDifferentConstraint(), match_variables)

    for i in range(len(match_variables)):
        for j in range(i + 1, len(match_variables)):
            problem.addConstraint(three_hours, [match_variables[i], match_variables[j]])

    solution = problem.getSolution()
    if solution:
        print(solution)
    else:
        print("No solution")
