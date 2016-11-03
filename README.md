# CoLabNotes

Using Firebase Real Time Database  `fbrtdb`

# Firebase RTDB Strucutre:

```
// An index to track Ada's notes
{
    "users": {
        "alovelace": {
            "name": "Ada Lovelace",
            // Index Ada's notes in her app
            "notes": {
                // the value here doesn't matter, just that the key exists
                "techpioneers": true,
                "womentechmakers": true,
                ...
            }
        },
        ...
    },

    "notes": {
        "techpioneers": {
            "name": "Historical Tech Pioneers",
            "owner": "alovelace",
            "members": {
                "alovelace": true,
                "ghopper": true,
                "eclarke": true
            }
            "data": "Since the 19th century...",
            "commits": {
                "alovelace": {
                    "commit_1": {
                        description : "Typo Fixed"
                        data : "Since, the 19th century...",
                        time : "29th Oct 2016, 3:07 PM"
                    },
                    "commit_2": {
                        description : "Reference Added"
                        data : "Since, the 19th century...",
                        time : "3rd Nov 2016, 9:31 AM"
                    },
                    ...
                },
                ...
            }
        },
        ...
    }
}

```