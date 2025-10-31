# smart_track.py
from __future__ import print_function
import os
print("Current working directory:", os.getcwd())
import datetime
import os.path
from google.auth.transport.requests import Request
from google.oauth2.credentials import Credentials
from google_auth_oauthlib.flow import InstalledAppFlow
from googleapiclient.discovery import build

# Add both Calendar and Classroom permissions
SCOPES = [
    'https://www.googleapis.com/auth/calendar',
    'https://www.googleapis.com/auth/classroom.courses.readonly',
    'https://www.googleapis.com/auth/classroom.coursework.me.readonly'
]

def main():
    creds = None
    if os.path.exists('token.json'):
        creds = Credentials.from_authorized_user_file('token.json', SCOPES)
    if not creds or not creds.valid:
        if creds and creds.expired and creds.refresh_token:
            creds.refresh(Request())
        else:
           flow = InstalledAppFlow.from_client_secrets_file
           
    r"C:"C:\r"Users\dsp13\OneDrive\Documents\GitHub\Smart-Track-System\credentials.json.json"on",
    SCOPES)
           )
           creds = flow.run_local_server(port=0)
        with open('token.json', 'w') as token:
            token.write(creds.to_json())

    # Connect to both APIs
    classroom_service = build('classroom', 'v1', credentials=creds)
    calendar_service = build('calendar', 'v3', credentials=creds)

    # Step 1: Get all courses
    results = classroom_service.courses().list(pageSize=10).execute()
    courses = results.get('courses', [])

    if not courses:
        print('No courses found.')
        return

    for course in courses:
        print(f"\n📘 Course: {course['name']}")
        
        # Step 2: Get upcoming assignments
        coursework = classroom_service.courses().courseWork().list(courseId=course['id']).execute().get('courseWork', [])
        for work in coursework:
            title = work['title']
            due = work.get('dueDate')
            due_time = work.get('dueTime')

            if due and due_time:
                due_datetime = datetime.datetime(
                    due['year'], due['month'], due['day'],
                    due_time.get('hours', 0), due_time.get('minutes', 0)
                )
                event = {
                    'summary': f"{course['name']} - {title}",
                    'description': 'Auto-added from Google Classroom',
                    'start': {
                        'dateTime': due_datetime.isoformat(),
                        'timeZone': 'Asia/Kolkata',
                    },
                    'end': {
                        'dateTime': (due_datetime + datetime.timedelta(hours=1)).isoformat(),
                        'timeZone': 'Asia/Kolkata',
                    },
                }
                calendar_service.events().insert(calendarId='primary', body=event).execute()
                print(f"✅ Added to Calendar: {title}")

if __name__ == '__main__':
    main()
