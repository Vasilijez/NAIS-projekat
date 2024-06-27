import json
import random
from datetime import datetime, timedelta
import os 

income_sources = {
    "Membership Fees": "Payments from club members for annual membership.",
    "Sponsorship": "Financial support from businesses in exchange for advertising and promotion.",
    "TV Rights": "Revenue from broadcasting rights for televised matches.",
    "Donations": "Voluntary contributions from supporters and benefactors.",
    "Merchandise Sales": "Income from selling club-branded merchandise.",
    "Matchday Ticket Sales": "Revenue from selling tickets for individual matches.",
    "Season Ticket Sales": "Income from selling season-long tickets to fans.",
    "Player Transfers": "Revenue from transferring players to other clubs.",
    "Hospitality Packages": "Sales of premium matchday experiences including catering and VIP access.",
    "Corporate Partnerships": "Income from collaborations with businesses for mutual benefit.",
    "Stadium Tours": "Revenue from guided tours of the club’s stadium.",
    "Concessions": "Income from food and beverage sales at the stadium.",
    "Training Camps": "Fees collected for organizing and running training camps.",
    "Fan Club Memberships": "Payments from fans joining official club fan clubs.",
    "Digital Content Sales": "Revenue from selling digital media, such as videos and online content.",
    "Club Events": "Income from organizing events like fan meet-ups, dinners, and galas.",
    "Advertising Revenue": "Money earned from advertising space on club platforms and stadium.",
    "Endorsements": "Payments from endorsing products or services by the club or its players.",
    "Grants": "Financial support from government or sports organizations.",
    "Licensing Fees": "Income from licensing the club’s brand and intellectual property.",
    "Youth Academy Fees": "Fees from young players training in the club’s academy.",
    "Loan Fees": "Revenue from loaning players to other clubs.",
    "Prize Money": "Income from winning competitions and tournaments.",
    "Fundraising Events": "Revenue from events organized to raise funds for the club."
}

# Define sources for random selection
source_names = list(income_sources.keys())
source_descriptions = list(income_sources.values())
sources = [
    "Redmond United Supporters Club",
    "TechCorp International",
    "Sports Network Broadcasting",
    "GlobeTech Enterprises",
    "FutureTech Solutions",
    "HealthPro Supplements",
    "EcoGreen Energy",
    "FinServe Banking",
    "EduWorld Academy",
    "StarMedia Productions"
]

def generate_income_record():
    timestamp = datetime(2022, 1, 1) + timedelta(days=random.randint(0, 730))
    description = random.choice(source_names)
    amount = round(random.uniform(10000, 500000), 2)
    source = random.choice(sources)
    
    return {
        "incomeCreationTimestamp": timestamp.isoformat(),
        "description": description,
        "amount": amount,
        "source": source
    }

def generate_200_records():
    records = [generate_income_record() for _ in range(200)]
    return records

if __name__ == "__main__":
    income_data = generate_200_records()
    file_path = os.path.join(os.getcwd(), "football_club_income_data.json")
    with open(file_path, "w") as outfile:
        json.dump(income_data, outfile, indent=2)

    print(f"Generated 200 income records. Saved to {file_path}")

    
    