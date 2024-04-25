/**
 * 密码重置
 */
@IgnoreAuth
@RequestMapping(value = "/resetPass")
public R resetPass(String username, HttpServletRequest request) {
    UsersEntity user = usersService.selectOne(new EntityWrapper<UsersEntity>().eq("username", username));
    if (user == null) {
        return R.error("账号不存在");
    }
    user.setPassword("123456");
    usersService.update(user, null);
    return R.ok("密码已重置为：123456");
}

/**
 * 列表
 */
@RequestMapping("/page")
public R page(@RequestParam Map<String, Object> params, UsersEntity user) {
    EntityWrapper<UsersEntity> ew = new EntityWrapper<UsersEntity>();
    PageUtils page = usersService.queryPage(params,
            MPUtil.sort(MPUtil.between(MPUtil.allLike(ew, user), params), params));
    return R.ok().put("data", page);
}

/**
 * 列表
 */
@RequestMapping("/list")
public R list(UsersEntity user) {
    EntityWrapper<UsersEntity> ew = new EntityWrapper<UsersEntity>();
    ew.allEq(MPUtil.allEQMapPre(user, "user"));
    return R.ok().put("data", usersService.selectListView(ew));
}

/**
 * 信息
 */
@RequestMapping("/info/{id}")
public R info(@PathVariable("id") String id) {
    UsersEntity user = usersService.selectById(id);
    return R.ok().put("data", user);
}

/**
 * 获取用户的session用户信息
 */
@RequestMapping("/session")
public R getCurrUser(HttpServletRequest request) {
    Integer id = (Integer) request.getSession().getAttribute("userId");
    UsersEntity user = usersService.selectById(id);
    return R.ok().put("data", user);
}

/**
 * 保存
 */
@PostMapping("/save")
public R save(@RequestBody UsersEntity user) {
    // ValidatorUtils.validateEntity(user);
    if (usersService.selectOne(new EntityWrapper<UsersEntity>().eq("username", user.getUsername())) != null) {
        return R.error("用户已存在");
    }
    user.setPassword("123456");
    usersService.insert(user);
    return R.ok();
}

/**
 * 修改
 */
@RequestMapping("/update")
public R update(@RequestBody UsersEntity user) {
    usersService.updateById(user);// 全部更新
    return R.ok();
}

/**
 * 删除
 */
@RequestMapping("/delete")
public R delete(@RequestBody Long[] ids) {
    usersService.deleteBatchIds(Arrays.asList(ids));
    return R.ok();
}