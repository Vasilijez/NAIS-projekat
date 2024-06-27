import numpy as np
import pandas as pd
import random 
from datetime import datetime

''' 1. Generisanje DataFrame-a Fan-a '''

random_usernames_genders = pd.read_csv('random_usernames_genders.csv')
random_players = ['Ronaldo', 'Neymar', 'Mbappe', 'Zivkovic', 'Tadic', 'Rajkovic', 'Vlahovic', 'Mitrovic', 'Pavlovic', 'Samardzic', 'Ilic', 'Milenkovic', 'Birmancevic', 'Lukic', 'Jovic', 'Veljkovic']

fans_df = pd.DataFrame(columns=['Level', 'PointsNumber', 'Username', 'FanType', 'FavouritePlayer', 'DateOfBirth', 'Gender'])

fans_df['Username'] = random_usernames_genders['Username']
fans_df['Gender'] = random_usernames_genders['Gender']

# Generisanje nasumicnih datuma
def random_date(start, end):
    return start + (end - start) * random.random()
    
start_date = datetime.strptime('1950-01-01', '%Y-%m-%d')
end_date = datetime.strptime('2010-01-01', '%Y-%m-%d')

fans_df['FavouritePlayer'] = np.random.choice(random_players, size=1000)
fans_df['DateOfBirth'] = [random_date(start_date, end_date).strftime('%Y-%m-%d') for _ in range(1000)]

# Odredjivanje tipa navijaca na osnovu datuma rodjenja
def categorize_fan(dateOfBirth):
    year = int(dateOfBirth.split('-')[0])
    if 1950 <= year < 1970:
        return 'Pensioner'
    if 1970 <= year < 1990:
        return 'Standard'
    if 1990 <= year < 2005:
        return 'Student'
    if 2005 <= year < 2015:
        return 'Junior'

fans_df['FanType'] = fans_df['DateOfBirth'].apply(categorize_fan)

print(fans_df)


''' 2. Generisanje DataFrame-a Seat-a '''

# Postavio sam da ima 25 mesta po sektoru prema onoj semi koju imam inicijalno

rows = []

j = 1
for i in range(25):
    if i not in (0, 4, 11, 12, 13, 20, 24):
        for _ in range(25):
            single_row = {
                'SeatNumber': 'SN' + str(j),
                'Sector': i,
                'Tribune': pd.NA
            }
            if i in (1, 2, 3, 6, 7, 8):
                single_row['Tribune'] = 'East'
            elif i in (5, 10, 15):
                single_row['Tribune'] = 'North'
            elif i in (9, 14, 19):
                single_row['Tribune'] = 'South'
            else:
                single_row['Tribune'] = 'West'

            rows.append(single_row)
            j += 1

seat_df = pd.DataFrame(rows)


''' 3. Generisanje DataFrame-a FootballMatch '''

rows = [
    {'FootballMatchID': 'FM1', 'StartTime':  datetime.strptime('2014-01-01', '%Y-%m-%d'), 'OpponentName': 'FK Partizan', 'Result': '1 1'},
    {'FootballMatchID': 'FM2', 'StartTime':  datetime.strptime('2015-01-01', '%Y-%m-%d'), 'OpponentName': 'FK Zvezda', 'Result': '1 2'},
    {'FootballMatchID': 'FM3', 'StartTime':  datetime.strptime('2016-01-01', '%Y-%m-%d'), 'OpponentName': 'FK Mladost', 'Result': '2 1'},
    {'FootballMatchID': 'FM4', 'StartTime':  datetime.strptime('2017-01-01', '%Y-%m-%d'), 'OpponentName': 'FK Barcelona', 'Result': '3 1'},
    {'FootballMatchID': 'FM5', 'StartTime':  datetime.strptime('2018-01-01', '%Y-%m-%d'), 'OpponentName': 'FK Real Madrid', 'Result': '0 0'},
    {'FootballMatchID': 'FM6', 'StartTime':  datetime.strptime('2019-01-01', '%Y-%m-%d'), 'OpponentName': 'FK Dortmund', 'Result': '1 0'},
    {'FootballMatchID': 'FM7', 'StartTime':  datetime.strptime('2020-01-01', '%Y-%m-%d'), 'OpponentName': 'FK Proleter', 'Result': '5 0'},
    {'FootballMatchID': 'FM8', 'StartTime':  datetime.strptime('2021-01-01', '%Y-%m-%d'), 'OpponentName': 'FK Dinamo', 'Result': '6 0'},
    {'FootballMatchID': 'FM9', 'StartTime':  datetime.strptime('2022-01-01', '%Y-%m-%d'), 'OpponentName': 'FK Rad', 'Result': '3 3'},
    {'FootballMatchID': 'FM10', 'StartTime':  datetime.strptime('2023-01-01', '%Y-%m-%d'), 'OpponentName': 'FK Zemun', 'Result': '1 0'},
    {'FootballMatchID': 'FM11', 'StartTime':  datetime.strptime('2024-01-01', '%Y-%m-%d'), 'OpponentName': 'FK Ferencvaros', 'Result': '0 2'},
]

football_match_df = pd.DataFrame(rows)

print(football_match_df)


