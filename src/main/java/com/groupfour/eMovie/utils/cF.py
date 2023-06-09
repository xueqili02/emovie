import pandas as pd
from math import sqrt
import ast
import sys

if __name__ == '__main__':

    rating= pd.read_csv('src/main/java/com/groupfour/eMovie/utils/ratings_small.csv')
    rating.drop(columns=['timestamp'], inplace=True)

    params_str = sys.argv[1]
    user = ast.literal_eval(params_str)
    # user = [
    #            {'id': 1, 'rating':4},
    #             {'id': 2, 'rating':2.5},
    #             {'id': 296, 'rating':3},
    #             {'id':1274, 'rating':4.5},
    #             {'id':1968	, 'rating':5}
    #          ]

    inputMovie = pd.DataFrame(user)
    users = rating[rating['movieId'].isin(inputMovie['id'].tolist())]
    userSubsetGroup = users.groupby(['userId'])
    userSubsetGroup = sorted(userSubsetGroup, key=lambda x: len(x[1]), reverse=True)
    userSubsetGroup = userSubsetGroup[0:500]

    pearsonCorDict = {}
    #For every user group in subset
    for name, group in userSubsetGroup:

        group = group.sort_values(by='movieId')
        inputMovie = inputMovie.sort_values(by='id')
        n = len(group)

        temp = inputMovie[inputMovie['id'].isin(group['movieId'].tolist())]

        tempRatingList = temp['rating'].tolist()

        tempGroupList = group['rating'].tolist()

        Sxx = sum([i ** 2 for i in tempRatingList]) - pow(sum(tempRatingList), 2) / float(n)
        Syy = sum([i ** 2 for i in tempGroupList]) - pow(sum(tempGroupList), 2) / float(n)
        Sxy = sum(i * j for i, j in zip(tempRatingList, tempGroupList)) - sum(tempRatingList) * sum(tempGroupList) / float(n)
        a = name[0]

        if Sxx != 0 and Syy != 0:
            pearsonCorDict[a] = Sxy/sqrt(Sxx*Syy)
        else:
            pearsonCorDict[a] = 0

    pearsonDF = pd.DataFrame.from_dict(pearsonCorDict, orient='index')
    pearsonDF.columns = ['similarityIndex']
    pearsonDF['userId'] = pearsonDF.index
    pearsonDF.index = range(len(pearsonDF))

    topUsers = pearsonDF.sort_values(by='similarityIndex', ascending=False)
    topUsers =topUsers[0:20]
    topUsersRating=topUsers.merge(rating, left_on='userId', right_on='userId', how='inner')

    topUsersRating['weightedRating'] = topUsersRating['similarityIndex'] * topUsersRating['rating']
    tempTopUsersRating = topUsersRating.groupby('movieId').sum()[['similarityIndex', 'weightedRating']]
    tempTopUsersRating.columns = ['sum_similarityIndex', 'sum_weightedRating']
    tempTopUsersRating.head()
    recommendation_df = pd.DataFrame()

    recommendation_df['recommendation index'] = tempTopUsersRating['sum_weightedRating'] / tempTopUsersRating[
        'sum_similarityIndex']
    recommendation_df['movieId'] = tempTopUsersRating.index
    recommendation_df = recommendation_df.sort_values(by='recommendation index', ascending=False)

    resultdf = recommendation_df.head(30)
    print(resultdf['movieId'])
