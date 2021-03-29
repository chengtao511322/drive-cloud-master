import request from '@/utils/request'

                        
// 分页查询合作驾校用户列表
export function listPageSchoolUser(query) {
  return request({
    url: '/admin/schoolUser/pageList',
    method: 'get',
    params: query
  })
}


// 分页查询合作驾校用户列表
export function listSchoolUser(query) {
  return request({
    url: '/admin/schoolUser/findList',
    method: 'get',
    params: query
  })
}

// 查询合作驾校用户详细
export function getSchoolUser(id) {
  return request({
    url: '/admin/schoolUser/' + id,
    method: 'get'
  })
}

// 新增合作驾校用户
export function addSchoolUser(data) {
  return request({
    url: '/admin/schoolUser',
    method: 'post',
    data: data
  })
}

// 修改合作驾校用户
export function updateSchoolUser(data) {
  return request({
    url: '/admin/schoolUser',
    method: 'put',
    data: data
  })
}

// 删除合作驾校用户
export function delSchoolUser(id) {
  return request({
    url: '/admin/schoolUser/' + id,
    method: 'delete'
  })
}


// 状态启用/停用合作驾校用户
export function changeStatus(data) {
  return request({
    url: '/admin/schoolUser/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出合作驾校用户
export function exportSchoolUser(query) {
  return request({
    url: '/admin/schoolUser/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}