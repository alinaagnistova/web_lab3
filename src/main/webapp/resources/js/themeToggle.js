const themeToggle = document.getElementById('theme-toggle');
const changeThemeToDark = () => {
    document.documentElement.setAttribute("data-theme", "dark");
    localStorage.setItem("data-theme", 'dark');

}
const changeThemeToLight = () => {
    document.documentElement.setAttribute("data-theme", "light");
    localStorage.setItem("data-theme", 'light');
}
changeThemeToLight();
themeToggle.addEventListener('click', () => {
    let theme = localStorage.getItem('data-theme');
    if (theme === 'dark' || theme === null || theme === undefined) {
        changeThemeToLight();
    } else {
        changeThemeToDark();
    }
});