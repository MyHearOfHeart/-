import time
from selenium import webdriver

driver = webdriver.Chrome()
#进入首页
driver.get('http://localhost:8081/index')     #get方式请求url
time.sleep(5)
#点击登录
driver.find_elements_by_xpath('//div[@class="nav-menu"]//li')[2].click()
time.sleep(5)
text = driver.find_elements_by_xpath('//input[@class="el-input__inner"]')[0]
text.send_keys('18923456@qq.com')
text = driver.find_elements_by_xpath('//input[@class="el-input__inner"]')[1]
text.send_keys('123456')
#按下登录按钮
driver.find_elements_by_xpath('//button[@class="el-button el-button--primary"]')[0].click()
time.sleep(5)
#点击首页的第一个视频进入视频播放处
driver.find_elements_by_xpath('//div[@class="el-card is-always-shadow"]')[0].click()
time.sleep(5)
#点击进入用户管理中心
driver.find_elements_by_xpath('//div[@class="nav-menu"]//li')[2].click()
