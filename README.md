Frontend tech stack:
1. JSF with PrimeFaces
2. Angular

Backend tech stack:
1. MySQL database
2. Enterprise Java Beans

Instructions to run application
1. Open the project using NetBeans
2. Create a MySQL database (perfectMatch)
3. Clean and build the main enterprise application
4. Deploy the main application
5. To run the JSF application, open a browser and use localhost:8080/PerfectMatch

Code styling
1. Use tab spaces
2. To auto-format in NetBeans, use alt-shift-f
3. Leave an empty line between methods
4. Camel case for variable and method names

**Important**
1. When adding new features, do it on a new branch first.
2. Name the branch something like this: anthony/registration_feature
3. Then after completed, create a pull request and if no conflict, merge with main branch.
4. This is to avoid merge conflicts as much as possible.

**How to use git branch**
1. Create local branch: git branch *name*/*branch name*
2. Checkout to newly create branch: git checkout *name*/*branch name*
3. Push local branch to github and set local branch to track remote branch: git push --set-upstream origin *name*/*branch name*

**WHEN PUSHING TO GITHUB**
1. Make sure you are pushing to the correct branch
2. Check which branch your local branch is tracking: git branch -vv
3. Safe method to push to correct branch: git push origin *name*/*branch name*:*name*/*branch name*, where name and branch name is the same to the left and right of the colon
