from flask import Flask, jsonify, request

app = Flask(__name__)

USERS = {
	'priths.jaunjale': 'Prithvi Jaunjale',
	'chrisevans73': 'Chris Evans',
	'boogeyman': 'JohnWick'
}
TODOS = {
	'priths.jaunjale': ['ProjectA', 'ProjectB'],
	'chrisevans73': ['ProjectB', 'ProjectC'],
	'boogeyman': ['ProjectC', 'ProjectD'],
}

@app.route('/help', methods = ['GET'])
def help():
	return jsonify({
        'Routes available': [
            {'name': 'getallusers', 'method': 'GET'},
            {'name': 'getalltodos', 'method': 'GET'}, 
            {'name': 'getuser', 'method': 'POST'}, 
            {'name': 'gettodo', 'method': 'GET'}
        ]
    })


@app.route('/getallusers', methods = ['GET'])
def get_all_users():
	global USERS
	return jsonify(USERS)

@app.route('/getalltodos', methods = ['GET'])
def get_all_todos():
	global TODOS
	return jsonify(TODOS)

@app.route('/getuser', methods = ['POST'])
def get_user():
    global USERS
    username = request.json['username']
    return jsonify({username: USERS[username]})

@app.route('/gettodo', methods = ['POST'])
def get_todo():
    global TODOS
    username = request.json['username']
    return jsonify({username: TODOS[username]})

# driver function
if __name__ == '__main__':
	app.run(debug = True)
