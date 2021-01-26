import request from '@/utils/request'


// 分页查询推广商信息列表
export function listPageRecommendUser(query) {
  return request({
    url: '/admin/recommendUser/pageList',
    method: 'post',
    params: query
  })
}


// 分页查询推广商信息列表
export function listRecommendUser(query) {
  return request({
    url: '/admin/recommendUser/findList',
    method: 'post',
    params: query
  })
}

// 查询推广商信息详细
export function getRecommendUser(id) {
  return request({
    url: '/admin/recommendUser/' + id,
    method: 'get'
  })
}

// 新增推广商信息
export function addRecommendUser(data) {
  return request({
    url: '/admin/recommendUser',
    method: 'post',
    data: data
  })
}

// 修改推广商信息
export function updateRecommendUser(data) {
  return request({
    url: '/admin/recommendUser',
    method: 'put',
    data: data
  })
}

// 删除推广商信息
export function delRecommendUser(id) {
  return request({
    url: '/admin/recommendUser/' + id,
    method: 'delete'
  })
}


// 状态启用/停用推广商信息
export function changeStatus(data) {
  return request({
    url: '/admin/recommendUser/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出推广商信息
export function exportRecommendUser(query) {
  return request({
    url: '/admin/recommendUser/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}