import requests
s = requests.session()
res = s.post('http:114.115.142.42/Operation/CreatePicSet',{'managerId':'1','PicSetName':'beach'}
print(res.text)
